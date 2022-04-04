package com.benahmed.gestiondestock.service.strategy;

import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.flickr4java.flickr.FlickrException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContexte {
    private final BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContexte(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determinStrategy(context);
        return strategy.savePhoto(id,photo,title);
    }

    private void determinStrategy(String context){
        final String beanName = context + "Strategy";
        switch (context){
            case "article":
                strategy = beanFactory.getBean(beanName,saveArticlePhoto.class);
                break;
            case "client":
                strategy = beanFactory.getBean(beanName,saveClientPhoto.class);
                break;
            case "entreprise":
                strategy = beanFactory.getBean(beanName,saveEntreprisePhoto.class);
                break;
            case "fournisseur":
                strategy = beanFactory.getBean(beanName, saveFournisseurPhoto.class);
                break;
            case "utilisateur":
                strategy = beanFactory.getBean(beanName,saveUtilisateurPhoto.class);
                break;
            default: throw new InvalidOperationException("inconnue contexte", ErrorCodes.UNKNOW_CONTEXT);
        }
    }
}
