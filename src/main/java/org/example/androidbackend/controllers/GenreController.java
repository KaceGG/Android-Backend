package org.example.androidbackend.controllers;

import org.example.androidbackend.models.Genre;
import org.example.androidbackend.repositories.GenreRepository;
import org.example.androidbackend.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/add")
    public boolean addGenre(@RequestParam(value = "name") String genreName) {
        return genreService.addGenre(genreName);
    }

    @PostMapping("/update")
    public boolean updateGenre(@RequestParam(value = "id") int genreId,
                               @RequestParam(value = "name") String genreName) {
        return genreService.updateGenre(genreId, genreName);
    }

    @GetMapping("/allGenres")
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Genre> getGenreById(@PathVariable Long id) {
        return genreRepository.findById(id);
    }
}
