package org.example.androidbackend.services.Impl;

import org.example.androidbackend.DTO.GenreDTO;
import org.example.androidbackend.DTO.MovieDTO;
import org.example.androidbackend.DTO.TicketDTO;
import org.example.androidbackend.DTO.UserDTO;
import org.example.androidbackend.models.Movie;
import org.example.androidbackend.models.Ticket;
import org.example.androidbackend.models.User;
import org.example.androidbackend.repositories.GenreRepository;
import org.example.androidbackend.repositories.MovieRepository;
import org.example.androidbackend.repositories.TicketRepository;
import org.example.androidbackend.repositories.UserRepository;
import org.example.androidbackend.services.TicketService;
import org.example.androidbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public ResponseEntity<String> addTicket(Long id, String token) {
        String email = userService.Authentication(token);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exits");
        }
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie does not exits");
        }
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovie(movie);
        ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.OK).body("Booking ticket success");
    }

    @Override
    public List<TicketDTO> findTicketsByUserId(String token) {
        String email = userService.Authentication(token);
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        List<Ticket> allTickets = ticketRepository.findAll();
        List<Ticket> userTickets = allTickets.stream()
                .filter(ticket -> ticket.getUser().getId() == user.getId())
                .toList();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : userTickets) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setId(ticket.getId());
            Movie movie = ticket.getMovie();
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDescription(movie.getDescription());

            movieDTO.setImage(movie.getImage());
            movieDTO.setDirector(movie.getDirector());
            movieDTO.setCast(movie.getCast());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setRating(movie.getRating());
            Set<GenreDTO> genres = genreRepository.findGenresByMoviesId(movie.getId()).stream().map(
                    genre -> {
                        GenreDTO genreDTO = new GenreDTO();
                        genreDTO.setId(genre.getId());
                        genreDTO.setName(genre.getName());
                        return genreDTO;
                    }
            ).collect(Collectors.toSet());
            movieDTO.setGenres(genres);
            ticketDTO.setMovie(movieDTO);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            ticketDTO.setUser(userDTO);
            ticketDTOs.add(ticketDTO);
        }
        return ticketDTOs;
    }

    @Override
    public List<TicketDTO> getAllTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setId(ticket.getId());
            Movie movie = ticket.getMovie();
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDescription(movie.getDescription());

            movieDTO.setImage(movie.getImage());
            movieDTO.setDirector(movie.getDirector());
            movieDTO.setCast(movie.getCast());
            movieDTO.setDuration(movie.getDuration());
            movieDTO.setRating(movie.getRating());
            Set<GenreDTO> genres = genreRepository.findGenresByMoviesId(movie.getId()).stream().map(
                    genre -> {
                        GenreDTO genreDTO = new GenreDTO();
                        genreDTO.setId(genre.getId());
                        genreDTO.setName(genre.getName());
                        return genreDTO;
                    }
            ).collect(Collectors.toSet());
            movieDTO.setGenres(genres);
            ticketDTO.setMovie(movieDTO);
            User user = ticket.getUser();
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            ticketDTO.setUser(userDTO);
            ticketDTOs.add(ticketDTO);
        }
        return ticketDTOs;
    }
}
