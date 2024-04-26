package org.example.androidbackend.services;

import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.requests.DeleteMovieRequest;
import org.example.androidbackend.requests.MovieRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MovieService {

    boolean addMovie(
            String title,
            String description,
            MultipartFile image,
            String director,
            String cast,
            int duration,
            float rating,
            List<Long> genreIds
    ) throws IOException;

//    boolean addMovie(MovieRequest movieRequest) throws IOException;

    List<MovieDTO> getAllMovie();
    MovieDTO getDetailMovie(Long id);

    boolean saveMovieDetail(Long movieId,
                            String title,
                            String description,
                            MultipartFile image,
                            String director,
                            String cast,
                            int duration,
                            float rating,
                            List<Long> genreIds) throws IOException;

    void deleteMovieByIds(DeleteMovieRequest deleteMovieRequest);
}
