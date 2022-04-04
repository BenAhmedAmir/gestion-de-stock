package com.benahmed.gestiondestock.controller;

import com.benahmed.gestiondestock.controller.api.PhotoApi;
import com.benahmed.gestiondestock.service.strategy.StrategyPhotoContexte;
import com.flickr4java.flickr.FlickrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController implements PhotoApi {
    private final StrategyPhotoContexte strategyPhotoContexte;
    @Autowired
    public PhotoController(StrategyPhotoContexte strategyPhotoContexte) {
        this.strategyPhotoContexte = strategyPhotoContexte;
    }

    @Override
    public Object savePhoto(String context, Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return strategyPhotoContexte.savePhoto(context,id, photo.getInputStream(), title);
    }
}
