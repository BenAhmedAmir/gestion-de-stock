package com.benahmed.gestiondestock.service.impl;

import com.benahmed.gestiondestock.DTO.ArticleDto;
import com.benahmed.gestiondestock.DTO.ClientDto;
import com.benahmed.gestiondestock.exception.EntityNotFoundException;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidEntityException;
import com.benahmed.gestiondestock.model.Article;
import com.benahmed.gestiondestock.repository.ArticleRepository;
import com.benahmed.gestiondestock.service.ArticleService;
import com.benahmed.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        // validation
        List<String> errors = ArticleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Article n'est pas valide {}", dto);
            throw new InvalidEntityException("l'Article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(
                articleRepository.save(ArticleDto.toEntity(dto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null) {
            log.error("id est null");
            return null;
        }
        return articleRepository.findById(id)
                .map(ArticleDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun article est trouvé avec l'id = " + id + "dans la base",
                        ErrorCodes.ARTICLE_NOT_FOUND
                ));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)) {
            log.error("Article code est null");
            return null;
        }
        return articleRepository.findByCodeArticle(codeArticle)
                .map(ArticleDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Aucun article est trouvé avec le code = " + codeArticle + "dans la base",
                        ErrorCodes.ARTICLE_NOT_FOUND
                ));
    }


    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                // method reference ::
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("id est null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
