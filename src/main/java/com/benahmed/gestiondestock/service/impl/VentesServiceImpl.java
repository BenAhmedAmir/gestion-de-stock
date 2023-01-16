package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.ArticleDto;
import com.benahmed.gestiondestock.DTO.LigneVenteDto;
import com.benahmed.gestiondestock.DTO.MvtStkDto;
import com.benahmed.gestiondestock.DTO.VentesDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.model.*;
import com.benahmed.gestiondestock.repository.ArticleRepository;
import com.benahmed.gestiondestock.repository.LigneVenteRepository;
import com.benahmed.gestiondestock.repository.VentesRepository;
import com.benahmed.gestiondestock.service.MvtStockService;
import com.benahmed.gestiondestock.service.VentesService;
import com.benahmed.gestiondestock.validator.VentesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private final VentesRepository ventesRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final ArticleRepository articleRepository;
    private final MvtStockService mvtStockService;

    @Autowired
    public VentesServiceImpl(VentesRepository ventesRepository,
                             LigneVenteRepository ligneVenteRepository,
                             ArticleRepository articleRepository, MvtStockService mvtStockService) {
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.articleRepository = articleRepository;
        this.mvtStockService = mvtStockService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("le vente est invalide {}", dto);
            throw new InvalidEntityException("le vente est invalide ", ErrorCodes.VENTE_NOT_VALID,
                    errors);
        }
        List<String> articleErrors = new ArrayList<>();
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Aucune article avec l'id = " + ligneVenteDto.getArticle().getId() +
                        "trouve dans la base");
            }
        });
        if (!articleErrors.isEmpty()) {
            log.error("un ou plusieurs article sont pas trouve dans la base {}", errors);
            throw new InvalidEntityException("un ou plusieurs article sont pas trouve dans la base {}",
                    ErrorCodes.ARTICLE_NOT_FOUND);
        }
        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);
        });
        return VentesDto.fromEntity(savedVentes);

    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null) {
            log.error("l'id est null");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente trouvé avec l'id = " + id,
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByVenteCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("le code vente est null");
            return null;
        }
        return ventesRepository.findByVenteCode(code)
                .map(VentesDto::fromEntity).orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente trouvé avec le code = " + code,
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            return;
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
        if(!ligneVentes.isEmpty()){
            throw new InvalidOperationException("impossible de supprimer une vente deja utilise dans ligne vente",
                    ErrorCodes.VENTE_ALREADY_IN_USE);
        }
        ventesRepository.deleteById(id);
    }

    private void updateMvtStk(LigneVente ligneVente) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvt.SORTIE)
                .sourceMvtStk(SourceMvtStk.VENTE)
                .quantite(ligneVente.getQunatite())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
        mvtStockService.entreeStock(mvtStkDto);
    }
}

