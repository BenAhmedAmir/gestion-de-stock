package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.MvtStkDto;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.TypeMvt;
import com.benahmed.gestiondestock.repository.MvtStkRepository;
import com.benahmed.gestiondestock.service.ArticleService;
import com.benahmed.gestiondestock.service.MvtStockService;
import com.benahmed.gestiondestock.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStockServiceImpl implements MvtStockService {

    private final MvtStkRepository repository;
    private final ArticleService articleService;
    @Autowired
    public MvtStockServiceImpl(MvtStkRepository repository, ArticleService articleService) {
        this.repository = repository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if(idArticle == null){
            log.warn("l'ID article est null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return repository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStockArticle(Integer idArticle) {
        return repository.findAllByArticleId(idArticle).stream()
                .map(MvtStkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto dto) {
        return entrePositif(dto, TypeMvt.ENTREE);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto dto) {
        return sortieNegatif(dto, TypeMvt.SORTIE);
    }

    @Override
    public MvtStkDto correctionStockPositif(MvtStkDto dto) {
        return entrePositif(dto,TypeMvt.CORRECTION_POSITIF);
    }

    @Override
    public MvtStkDto correctionStockNegatif(MvtStkDto dto) {
        return sortieNegatif(dto,TypeMvt.CORRECTION_NEGATIF);
    }
    private MvtStkDto entrePositif(MvtStkDto dto, TypeMvt typeMvt){
        List<String> errors = MvtStkValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("le mvt stock nest pas valide {} ", dto);
            throw new InvalidEntityException("le mvt stock nest pas valide " , ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        dto.setTypeMvt(typeMvt);
        dto.setQuantite(BigDecimal.valueOf(
                Math.abs(dto.getQuantite().doubleValue())
        ));

        return MvtStkDto.fromEntity(repository.save(MvtStkDto.toEntity(dto)));
    }
    private MvtStkDto sortieNegatif(MvtStkDto dto, TypeMvt typeMvt){
        List<String> errors = MvtStkValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("le mvt stock nest pas valide {} ", dto);
            throw new InvalidEntityException("le mvt stock nest pas valide " , ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        dto.setTypeMvt(typeMvt);
        dto.setQuantite(BigDecimal.valueOf(
                Math.abs(dto.getQuantite().doubleValue()) * -1
        ));

        return MvtStkDto.fromEntity(repository.save(MvtStkDto.toEntity(dto)));
    }
}
