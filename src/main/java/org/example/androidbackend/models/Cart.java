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
//public class Cart implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private int totalTicket;
//
////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
////    private List<Ticket> tickets = new ArrayList<>();
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JsonIgnore
////    @JoinColumn(name = "user_id")
////    private User user;
//}
