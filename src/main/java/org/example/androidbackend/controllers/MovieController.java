package org.example.androidbackend.controllers;

import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.repositories.MovieRepository;
import org.example.androidbackend.requests.MovieRequest;
import org.example.androidbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

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
        return movieService.getAllMovieDTO();
    }
}
