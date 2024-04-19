package org.example.androidbackend.services.Impl;

import org.example.androidbackend.DTO.GenreDTO;
import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.models.Genre;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.repositories.GenreRepository;
import org.example.androidbackend.repositories.MovieRepository;
import org.example.androidbackend.requests.MovieRequest;
import org.example.androidbackend.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Override
    public boolean addMovie(MovieRequest movieRequest) {
        if (movieRepository.existsByTitle(movieRequest.getTitle())) {
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
        for(Long id : movieRequest.getGenreIds()){
            Genre genre = genreRepository.findById(id).orElse(null);
            genres.add(genre);
        }
        movie.setGenres(genres);
        movieRepository.save(movie);
        return true;
    }

    @Override
    public List<MovieDTO> getAllMovieDTO() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDescription(movie.getDescription());
            movieDTO.setDirector(movie.getDirector());
            movieDTO.setCast(movie.getCast());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setRating(movie.getRating());
            movieDTO.setGenres(movie.getGenres().stream().map(genre -> {
                GenreDTO genreDTO = new GenreDTO();
                genreDTO.setId(genre.getId());
                genreDTO.setName(genre.getName());
                return genreDTO;
            }).collect(Collectors.toSet()));
            return movieDTO;
        }).collect(Collectors.toList());
    }
}