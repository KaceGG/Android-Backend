package org.example.androidbackend.services;


import org.example.androidbackend.DTO.TicketDTO;
import org.example.androidbackend.models.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    public ResponseEntity<String> addTicket(Long id, String token);

    public List<Ticket> findTicketsByUserId(String token);

    public List<TicketDTO> getAllTicket();
}
