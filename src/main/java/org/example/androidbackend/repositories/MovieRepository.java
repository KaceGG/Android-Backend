package org.example.androidbackend.repositories;

import org.example.androidbackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);

//    @Query("SELECT mv.genres FROM Movie mv WHERE mv.id = :movieId")
//    Set<Genre> findGenresByMovieId(@Param("movieId") Integer movieId);
}
