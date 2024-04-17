package org.example.androidbackend.services;

import org.example.androidbackend.models.Genre;

import java.util.List;

public interface GenreService {
    boolean addGenre(String genreName);
    boolean updateGenre(int id, String genreName);
}
