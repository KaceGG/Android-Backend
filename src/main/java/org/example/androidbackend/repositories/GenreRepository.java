package org.example.androidbackend.repositories;

import org.example.androidbackend.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);

    @Query("select g from Genre g join g.movies m where m.id = :movieId ")
    List<Genre> findGenresByMoviesId(@Param("movieId") Long movieId);
}
