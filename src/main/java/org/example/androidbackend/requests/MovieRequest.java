package org.example.androidbackend.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
public class MovieRequest {
    private int id;
    private String title;
    private String description;
    private MultipartFile image;
    private String director;
    private String cast;
    private int duration;
    private float rating;

    private List<Long> genreIds;
}
