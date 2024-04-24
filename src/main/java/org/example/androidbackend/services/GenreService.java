package org.example.androidbackend.services;

public interface GenreService {
    boolean addGenre(String genreName);
    boolean updateGenre(Long id, String genreName);
}
