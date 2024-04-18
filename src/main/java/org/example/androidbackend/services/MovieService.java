package org.example.androidbackend.services;

import org.example.androidbackend.requests.MovieRequest;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    boolean addMovie(MovieRequest movieRequest);
}
