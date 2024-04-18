package org.example.androidbackend.controllers;

import org.example.androidbackend.models.Genre;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.repository.MovieRepository;
import org.example.androidbackend.request.MovieRequest;
import org.example.androidbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/add")
    public boolean addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    @GetMapping("/all")
    public Set<Genre> getAllGenreToMovie(@RequestParam(value = "movieId") Integer id){
        return movieRepository.findGenresByMovieId(id);
    }
}
