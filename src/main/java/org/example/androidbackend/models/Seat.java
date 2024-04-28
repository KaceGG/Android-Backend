//package org.example.androidbackend.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "seat")
//public class Seat implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private int seatRow;
//    private int seatNumber;
//    private float price;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "auditorium_id")
//    @JsonIgnore
//    private Auditorium auditorium;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seat", orphanRemoval = true)
//    @JsonIgnore
//    private List<SeatStatus> listSeatStatus = new ArrayList<>();
//}
