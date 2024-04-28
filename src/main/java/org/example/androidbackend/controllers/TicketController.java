package org.example.androidbackend.controllers;


import org.example.androidbackend.models.Ticket;
import org.example.androidbackend.repositories.TicketRepository;
import org.example.androidbackend.requests.DeleteMovieRequest;
import org.example.androidbackend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/booking")
    public ResponseEntity<String> bookingTicket(@RequestParam(value = "movieId") Long id,
                                                @RequestHeader(name = "Authorization") String token) {
        return ticketService.addTicket(id, token);
    }

    @GetMapping("/detail")
    public List<Ticket> getTicketByUser(@RequestHeader(name = "Authorization") String token) {
        return ticketService.findTicketsByUserId(token);
    }

    @GetMapping("/getAll")
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteMoviesByIds(@RequestParam(value = "ticketId") Long ticketId) {
        try {
            ticketRepository.deleteById(ticketId);
            return new ResponseEntity<>("200", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("400", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}