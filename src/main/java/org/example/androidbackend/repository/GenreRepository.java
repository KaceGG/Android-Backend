package org.example.androidbackend.repository;

import org.example.androidbackend.models.Genre;
import org.example.androidbackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);
    Genre findById(int id);
}
