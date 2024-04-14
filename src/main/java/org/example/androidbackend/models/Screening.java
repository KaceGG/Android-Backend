package org.example.androidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "screening")
public class Screening implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private LocalDate start;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auditorium_id")
    @JsonIgnore
    private Auditorium auditorium;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "screening", orphanRemoval = true)
    @JsonIgnore
    private List<SeatStatus> listSeatStatus = new ArrayList<>();
}
