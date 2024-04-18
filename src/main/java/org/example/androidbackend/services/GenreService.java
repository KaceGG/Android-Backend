package org.example.androidbackend.services;

public interface GenreService {
    boolean addGenre(String genreName);
    boolean updateGenre(int id, String genreName);
}
