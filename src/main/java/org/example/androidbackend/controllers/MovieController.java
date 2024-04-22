package org.example.androidbackend.controllers;

import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.repositories.MovieRepository;
import org.example.androidbackend.requests.MovieRequest;
import org.example.androidbackend.services.FileStorageService;
import org.example.androidbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private MovieRepository movieRepository;

//    @PostMapping("/add")
//    public boolean addMovie(@RequestBody MovieRequest movieRequest) {
//        return movieService.addMovie(movieRequest);
//    }

    @PostMapping("/add")
    public boolean addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    @GetMapping("/all")
    public List<MovieDTO> getAllMovie(){
        return movieService.getAllMovie();
    }

    @GetMapping("/image/{path}")
    public ResponseEntity<Resource> getImageByPath(@PathVariable String path) throws MalformedURLException {
        Resource resource = fileStorageService.loadFile(path, "movie");
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }
}
