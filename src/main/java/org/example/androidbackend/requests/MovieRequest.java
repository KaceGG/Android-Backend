package org.example.androidbackend.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MovieRequest {
    private String title;
    private String description;
    private MultipartFile image;
    private String director;
    private String cast;
    private int duration;
    private float rating;
    private List<Long> genreIds;
}