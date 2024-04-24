package org.example.androidbackend.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

public interface FileStorageService {
    Resource loadFile(String filename, String type) throws MalformedURLException;
    void addImage(MultipartFile file, Long identifier, String type);
}
