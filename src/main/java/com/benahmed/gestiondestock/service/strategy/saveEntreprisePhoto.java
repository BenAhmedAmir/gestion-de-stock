package com.benahmed.gestiondestock.service.strategy;

import com.benahmed.gestiondestock.DTO.EntrepriseDto;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.service.EntrepriseService;
import com.benahmed.gestiondestock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("entrepriseStrategy")
@Slf4j
public class saveEntreprisePhoto implements Strategy<EntrepriseDto> {
    FlickrService flickrService;
    EntrepriseService entrepriseService;
    @Autowired
    public saveEntreprisePhoto(FlickrService flickrService, EntrepriseService entrepriseService) {
        this.flickrService = flickrService;
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        EntrepriseDto entrepriseDto = entrepriseService.findById(id);
        String urlPhoto =flickrService.savePic(photo, title);
        if (!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error lors de l'enregistrement de photo de l'entreprise ",
                    ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        entrepriseDto.setPhoto(urlPhoto);
        return entrepriseService.save(entrepriseDto);
    }
}
