package org.example.androidbackend.services;


import org.example.androidbackend.DTO.TicketDTO;
import org.example.androidbackend.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    ResponseEntity<String> addTicket(Long id, String token);

    List<TicketDTO> findTicketsByUserId(String token);

    List<TicketDTO> getAllTicket();
}
