package org.example.androidbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class SeatStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private SeatStatusE status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seatStatus", orphanRemoval = true)
    @JsonIgnore
    private List<Ticket> tickets;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screening_id")
    private Screening screening;
}
