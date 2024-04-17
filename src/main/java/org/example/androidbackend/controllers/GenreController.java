package org.example.androidbackend.controllers;

import org.example.androidbackend.models.Genre;
import org.example.androidbackend.repository.GenreRepository;
import org.example.androidbackend.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/add")
    public boolean addGenre(@RequestParam(value = "genreName") String genreName) {
        return genreService.addGenre(genreName);
    }

    @PostMapping("/update")
    public boolean updateGenre(@RequestParam(value = "id") int genreId,
                               @RequestParam(value = "genreName") String genreName) {
        return genreService.updateGenre(genreId, genreName);
    }

    @GetMapping("/allGenres")
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
