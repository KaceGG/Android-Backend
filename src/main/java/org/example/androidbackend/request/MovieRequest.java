package org.example.androidbackend.request;

import lombok.Data;

import java.util.List;

@Data
public class MovieRequest {
    private String title;
    private String description;
    //private MultipartFile image;
    private String director;
    private String cast;
    private int duration;
    private float rating;

    private List<Long> id;
}
