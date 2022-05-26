package gestion.ticket.controllers;

import gestion.ticket.models.Ticket;
import gestion.ticket.repository.TicketRepository;
import gestion.ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Client")
public class ClientController {

    @Autowired
    private TicketRepository ticketRepository;

    // liste des tickets du client
    @PostMapping("/User/{id}")
    public List<Ticket> ListTicker(@PathVariable Long id){
        return ticketRepository.getTicketByUser_Id(id);
    }

    // créer un ticket
    @PostMapping("/add")
    public Ticket AddTicket(@RequestBody Ticket ticket){
        ticket.setStatut("ouvert");
        return ticketRepository.save(ticket);
    }
}
