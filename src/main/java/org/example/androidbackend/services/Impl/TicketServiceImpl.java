package org.example.androidbackend.services.Impl;

import org.example.androidbackend.models.Movie;
import org.example.androidbackend.models.Ticket;
import org.example.androidbackend.models.User;
import org.example.androidbackend.repositories.MovieRepository;
import org.example.androidbackend.repositories.TicketRepository;
import org.example.androidbackend.repositories.UserRepository;
import org.example.androidbackend.services.TicketService;
import org.example.androidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public ResponseEntity<String> addTicket(Long id, String token) {
        String email = userService.Authentication(token);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exits");
        }
        Movie movie = movieRepository.findById(id).orElse(null);
        if(movie == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie does not exits");
        }
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovie(movie);
        ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.OK).body("Booking ticket success");
    }

    @Override
    public List<Ticket> findTicketsByUserId(String token) {
        String email = userService.Authentication(token);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null){
            return null;
        }
        return ticketRepository.findAll().stream()
                .filter(ticket -> ticket.getUser().getId() == user.getId())
                .collect(Collectors.toList());
    }
}
