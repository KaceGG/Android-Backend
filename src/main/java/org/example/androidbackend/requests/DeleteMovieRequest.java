package org.example.androidbackend.requests;

import lombok.Data;

import java.util.List;


@Data
public class DeleteMovieRequest {
    private List<Long> movieIds;
}
