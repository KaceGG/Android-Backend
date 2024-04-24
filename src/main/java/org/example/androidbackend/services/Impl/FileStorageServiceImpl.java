package org.example.androidbackend.services.Impl;

import org.example.androidbackend.services.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private static final Path location = Paths.get("uploads");

    @Override
    public Resource loadFile(String filename, String type) throws MalformedURLException {
        Path file = location.resolve(type).resolve(filename);
        Resource resource = new UrlResource(file.toUri());
        if(resource.exists() || resource.isReadable()){
            return resource;
        } else {
            throw new MalformedURLException("Could not read file: " + filename);
        }
    }

    @Override
    public void addImage(MultipartFile file, Long identifier, String type){
        try{
            Path movieDirectory = location.resolve(type);
            if(!Files.exists(movieDirectory)){
                Files.createDirectories(movieDirectory);
            }
            Path movieFile = movieDirectory.resolve(identifier + "_" + file.getOriginalFilename());
            Files.copy(file.getInputStream(), movieFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to add image", e);
        }
    }

//    public String addImage(MultipartFile file, Long identifier, String type) {
//        try {
//            Path movieDirectory = location.resolve(type);
//            if (!Files.exists(movieDirectory)) {
//                Files.createDirectories(movieDirectory);
//            }
//            Path movieFile = movieDirectory.resolve(identifier + "_" + file.getOriginalFilename());
//            Files.copy(file.getInputStream(), movieFile, StandardCopyOption.REPLACE_EXISTING);
//
//            // Tạo URL đầy đủ cho ảnh và trả về
//            String imageUrl = BASE_URL + "/api/movie/image/" + identifier + "_" + file.getOriginalFilename();
//            return imageUrl;
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to add image", e);
//        }
//    }
}
