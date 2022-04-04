package com.benahmed.gestiondestock.service.strategy;

import com.benahmed.gestiondestock.DTO.FournisseurDto;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.service.FlickrService;
import com.benahmed.gestiondestock.service.FournisseurService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.InputStream;

@Service("fournisseurStrategy")
@Slf4j
public class saveFournisseurPhoto implements Strategy<FournisseurDto>{
    FlickrService flickrService;
    FournisseurService fournisseurService;
    @Autowired
    public saveFournisseurPhoto(FlickrService flickrService, FournisseurService fournisseurService) {
        this.flickrService = flickrService;
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        FournisseurDto fournisseur =  fournisseurService.findById(id);
        String urlPhoto = flickrService.savePic(photo, title);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error lors de l'enregistrement de photo de fournisseur",
                    ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        fournisseur.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseur);
    }
}
