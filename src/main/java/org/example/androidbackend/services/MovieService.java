package org.example.androidbackend.services;

import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.requests.MovieRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MovieService {
    //    boolean addMovie(MovieRequest movieRequest);
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

    List<MovieDTO> getAllMovie();
}
