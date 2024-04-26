package org.example.androidbackend.controllers;

import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.services.FileStorageService;
import org.example.androidbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/add")
    public boolean addMovie(@RequestParam(value = "title") String title,
                            @RequestParam(value = "description") String description,
                            @RequestPart(value = "image") MultipartFile image,
                            @RequestParam(value = "director") String director,
                            @RequestParam(value = "cast") String cast,
                            @RequestParam(value = "duration") int duration,
                            @RequestParam(value = "rating") float rating,
                            @RequestParam(value = "genreIds") List<Long> genreIds) throws IOException {
        return movieService.addMovie(title, description, image, director, cast, duration, rating, genreIds);
    }

    @GetMapping("/all")
    public List<MovieDTO> getAllMovie() {
        return movieService.getAllMovie();
    }

    @GetMapping("/image/{path}")
    public ResponseEntity<Resource> getImageByPath(@PathVariable String path) throws MalformedURLException {
        Resource resource = fileStorageService.loadFile(path, "movie");
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }

    @GetMapping("/detail/{id}")
    public MovieDTO getDetailMovie(@PathVariable Long id){
        return movieService.getDetailMovie(id);
    }
}
