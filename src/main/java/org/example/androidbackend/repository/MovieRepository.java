package org.example.androidbackend.repository;

import org.example.androidbackend.models.Genre;
import org.example.androidbackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);

    @Query("SELECT mv.genres FROM Movie mv WHERE mv.id = :movieId")
    Set<Genre> findGenresByMovieId(@Param("movieId") Integer movieId);
}
