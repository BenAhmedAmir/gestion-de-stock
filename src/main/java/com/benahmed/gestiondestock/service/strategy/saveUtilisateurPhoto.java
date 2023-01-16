package com.benahmed.gestiondestock.service.strategy;

import com.benahmed.gestiondestock.DTO.UtilisateurDto;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.service.FlickrService;
import com.benahmed.gestiondestock.service.UtilisateurService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("utilisateurStrategy")
@Slf4j
public class saveUtilisateurPhoto implements Strategy<UtilisateurDto>{
    FlickrService flickrService;
    UtilisateurService utilisateurService;
    @Autowired
    public saveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        UtilisateurDto utilisateur = utilisateurService.findById(id);
        String urlPhoto = flickrService.savePic(photo,title);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error lors de l'enregistrement de photo de l'utilisateur",
                    ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        utilisateur.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateur);
    }
}
