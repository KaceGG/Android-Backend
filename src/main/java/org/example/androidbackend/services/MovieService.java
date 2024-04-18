package org.example.androidbackend.services;

import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.request.MovieRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    boolean addMovie(MovieRequest movieRequest);
}
