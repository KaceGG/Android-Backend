package org.example.androidbackend.services.Impl;

import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.models.Genre;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.repository.GenreRepository;
import org.example.androidbackend.repository.MovieRepository;
import org.example.androidbackend.request.MovieRequest;
import org.example.androidbackend.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public boolean addMovie(MovieRequest movieRequest) {
        if(movieRepository.existsByTitle(movieRequest.getTitle())){
            return false;
        }
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setDirector(movieRequest.getDirector());
        movie.setCast(movieRequest.getCast());
        movie.setDuration(movieRequest.getDuration());
        movie.setRating(movieRequest.getRating());
        Set<Genre> genres = new HashSet<>();
        for(Long id : movieRequest.getId()){
            Genre genre = genreRepository.findById(id).get();
            genres.add(genre);
        }
        movie.setGenres(genres);
        movieRepository.save(movie);
        return true;
    }
}
