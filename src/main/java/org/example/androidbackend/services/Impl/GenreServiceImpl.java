package org.example.androidbackend.services.Impl;

import org.example.androidbackend.models.Genre;
import org.example.androidbackend.repository.GenreRepository;
import org.example.androidbackend.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public boolean addGenre(String genreName) {
        if(genreRepository.existsByName(genreName)){
            return false;
        }
        else {
            Genre genre = new Genre();
            genre.setName(genreName);
            genreRepository.save(genre);
            return true;
        }
    }

    @Override
    public boolean updateGenre(int id, String genreName) {
        if(genreRepository.existsByName(genreName)){
            return false;
        } else {
            Genre genre = new Genre();
            genre.setId(id);
            genre.setName(genreName);
            genreRepository.save(genre);
            return true;
        }
    }
}
