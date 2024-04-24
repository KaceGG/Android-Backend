package org.example.androidbackend.repositories;

import org.example.androidbackend.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);
    List<Movie> findMoviesByGenresId(int genreId);
//    @Query("SELECT mv.genres FROM Movie mv WHERE mv.id = :movieId")
//    Set<Genre> findGenresByMovieId(@Param("movieId") Integer movieId);
}
