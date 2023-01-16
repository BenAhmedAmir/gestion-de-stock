package com.benahmed.gestiondestock.service.strategy;

import com.benahmed.gestiondestock.DTO.ClientDto;
import com.benahmed.gestiondestock.exception.ErrorCodes;
import com.benahmed.gestiondestock.exception.InvalidOperationException;
import com.benahmed.gestiondestock.service.ClientService;
import com.benahmed.gestiondestock.service.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("clientStrategy")
@Slf4j
public class saveClientPhoto implements Strategy<ClientDto>{
    FlickrService flickrService;
    ClientService clientService;
    @Autowired
    public saveClientPhoto(FlickrService flickrService, ClientService clientService) {
        this.flickrService = flickrService;
        this.clientService = clientService;
    }

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        ClientDto client = clientService.findById(id);
        String urlPhoto = flickrService.savePic(photo, title);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("error lors de l'enregistrement de photo du client",
                    ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        client.setPhoto(urlPhoto);
        return clientService.save(client);
    }
}
