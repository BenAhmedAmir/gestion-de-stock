package com.benahmed.gestiondestock.service.strategy;

import com.benahmed.gestiondestock.DTO.ArticleDto;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.service.ArticleService;
import com.benahmed.gestiondestock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("articleStrategy")
@Slf4j
public class saveArticlePhoto implements Strategy<ArticleDto> {
    ArticleService articleService;
    FlickrService flickrService;
    @Autowired
    public saveArticlePhoto(ArticleService articleService, FlickrService flickrService) {
        this.articleService = articleService;
        this.flickrService = flickrService;
    }

    @Override
    public ArticleDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        ArticleDto article = articleService.findById(id);
        String urlPhoto = flickrService.savePic(photo, title);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error lors de l'enregistrement de photo de l'article",
                    ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        article.setPhoto(urlPhoto);
        return articleService.save(article);
    }
}
