package org.example.androidbackend.services.Impl;

import org.example.androidbackend.DTO.GenreDTO;
import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.models.Genre;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.repositories.GenreRepository;
import org.example.androidbackend.repositories.MovieRepository;
import org.example.androidbackend.requests.DeleteMovieRequest;
import org.example.androidbackend.requests.MovieRequest;
import org.example.androidbackend.services.CloudService;
import org.example.androidbackend.services.FileStorageService;
import org.example.androidbackend.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private CloudService cloudService;

    //    @Override
//    public boolean addMovie(MovieRequest movieRequest) {
//        if (movieRepository.existsByTitle(movieRequest.getTitle())) {
//            return false;
//        }
//        Movie movie = new Movie();
//        movie.setTitle(movieRequest.getTitle());
//        movie.setDescription(movieRequest.getDescription());
//        movie.setDirector(movieRequest.getDirector());
//        movie.setCast(movieRequest.getCast());
//        movie.setDuration(movieRequest.getDuration());
//        movie.setRating(movieRequest.getRating());
//
//        Set<Genre> genres = new HashSet<>();
//        for (Long id : movieRequest.getGenreIds()) {
//            Genre genre = genreRepository.findById(id).orElse(null);
//            genres.add(genre);
//        }
//        movie.setGenres(genres);
//
//        movieRepository.save(movie);
//        return true;
//    }
    @Override
    public boolean addMovie(String title, String description, MultipartFile image, String director, String cast, int duration, float rating, List<Long> genreIds) throws IOException {
        if (movieRepository.existsByTitle(title)) {
            return false;
        }
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setDirector(director);
        movie.setCast(cast);
        movie.setDuration(duration);
        movie.setRating(rating);
        movie.setImage(cloudService.uploadImage(image));
        Set<Genre> genres = new HashSet<>();
        for (Long id : genreIds) {
            Genre genre = genreRepository.findById(id).orElse(null);
            genres.add(genre);
        }
        movie.setGenres(genres);
        movieRepository.save(movie);
        return true;
    }

    @Override
    public List<MovieDTO> getAllMovie() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream().map(movie -> {
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDescription(movie.getDescription());

            movieDTO.setImage(movie.getImage());
            movieDTO.setDirector(movie.getDirector());
            movieDTO.setCast(movie.getCast());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setRating(movie.getRating());

            Set<GenreDTO> genres = genreRepository.findGenresByMoviesId(movie.getId()).stream().map(
                    genre -> {
                        GenreDTO genreDTO = new GenreDTO();
                        genreDTO.setId(genre.getId());
                        genreDTO.setName(genre.getName());
                        return genreDTO;
                    }
            ).collect(Collectors.toSet());
            movieDTO.setGenres(genres);
            return movieDTO;

        }).collect(Collectors.toList());
    }

    @Override
    public MovieDTO getDetailMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) {
            return null;
        }
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());

        movieDTO.setImage(movie.getImage());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setCast(movie.getCast());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setRating(movie.getRating());
        Set<GenreDTO> genres = genreRepository.findGenresByMoviesId(movie.getId()).stream().map(
                genre -> {
                    GenreDTO genreDTO = new GenreDTO();
                    genreDTO.setId(genre.getId());
                    genreDTO.setName(genre.getName());
                    return genreDTO;
                }
        ).collect(Collectors.toSet());
        movieDTO.setGenres(genres);
        return movieDTO;
    }

    @Override
    public boolean saveMovieDetail(Long movieId, String title, String description, MultipartFile image, String director, String cast, int duration, float rating, List<Long> genreIds) throws IOException {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null || movie.getTitle().equals(title)) {
            return false;
        }
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setDirector(director);
        movie.setCast(cast);
        movie.setDuration(duration);
        movie.setRating(rating);
        movie.setImage(cloudService.uploadImage(image));
        Set<Genre> genres = new HashSet<>();
        for (Long id : genreIds) {
            Genre genre = genreRepository.findById(id).orElse(null);
            genres.add(genre);
        }
        movie.setGenres(genres);
        movieRepository.save(movie);
        return true;
    }

    @Override
    public void deleteMovieByIds(DeleteMovieRequest deleteMovieRequest) {
        for (Long movieId : deleteMovieRequest.getMovieIds()) {
            movieRepository.deleteById(movieId);
        }
    }
}