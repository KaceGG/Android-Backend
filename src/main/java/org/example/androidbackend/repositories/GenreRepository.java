package org.example.androidbackend.repositories;

import org.example.androidbackend.models.Genre;
import org.example.androidbackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);

    @Query("select g from Genre g join g.movies m where m.id = :movieId ")
    Set<Genre> findGenresByMoviesId(@Param("movieId") int movieId);
}
