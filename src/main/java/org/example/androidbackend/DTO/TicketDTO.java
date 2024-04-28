package org.example.androidbackend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.models.User;


@Data
public class TicketDTO {
    private int id;

    private MovieDTO movie;

    private UserDTO user;
}
