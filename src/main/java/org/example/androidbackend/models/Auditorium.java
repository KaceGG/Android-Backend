package org.example.androidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "auditorium")
public class Auditorium implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int seats_no;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditorium", orphanRemoval = true)
    @JsonIgnore
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditorium", orphanRemoval = true)
    @JsonIgnore
    private List<Screening> screenings = new ArrayList<>();
}
