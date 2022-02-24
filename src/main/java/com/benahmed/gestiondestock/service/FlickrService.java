package com.benahmed.gestiondestock.service;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface FlickrService {
    String savePic(InputStream picture, String title) throws FlickrException;
}
