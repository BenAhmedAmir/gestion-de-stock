package com.benahmed.gestiondestock.controller.api;

import com.benahmed.gestiondestock.utilis.constants;
import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(constants.APP_ROOT + "/photos")
public interface PhotoApi {
    @PostMapping(constants.APP_ROOT + "/photos/{id}/{title}/{context}")
    Object savePhoto(@PathVariable("context") String context, @PathVariable("id") Integer id,
                     @RequestPart("file") MultipartFile photo, String title) throws IOException, FlickrException;
}
