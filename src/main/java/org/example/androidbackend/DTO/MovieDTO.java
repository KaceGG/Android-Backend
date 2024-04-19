package org.example.androidbackend.DTO;

import lombok.Data;
import org.example.androidbackend.models.Genre;

import java.util.Set;


@Data
public class MovieDTO {
    private int id;
    private String title;
    private String description;
//    private String image;
    private String director;
    private String cast;
    private int duration;
    private float rating;

    private Set<GenreDTO> genres;
}
