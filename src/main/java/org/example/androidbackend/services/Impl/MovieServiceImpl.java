package org.example.androidbackend.services.Impl;

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
import java.util.Optional;
import java.util.Set;

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
        if (movieRequest.getGenreIds() == null || movieRequest.getGenreIds().isEmpty()) {
            logger.error("GenreIds list is null or empty. Cannot add movie without genres.");
            return false; // Trả về false và ghi log lỗi
        }
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setDescription(movieRequest.getDescription());
        movie.setDirector(movieRequest.getDirector());
        movie.setCast(movieRequest.getCast());
        movie.setDuration(movieRequest.getDuration());
        movie.setRating(movieRequest.getRating());

        Set<Genre> genres = new HashSet<>();
        for (Long id : movieRequest.getGenreIds()) {
            Optional<Genre> optionalGenre = genreRepository.findById(id);
            if (optionalGenre.isPresent()) {
                Genre genre = optionalGenre.get();
                genres.add(genre);
            } else {
                logger.error("Genre with id " + id + " not found.");
                return false;
            }
            movie.setGenres(genres);
            movieRepository.save(movie);
        }
        return true;
    }
}