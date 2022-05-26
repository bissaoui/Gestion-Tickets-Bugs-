package gestion.ticket.controllers;

import gestion.ticket.models.Ticket;
import gestion.ticket.repository.TicketRepository;
import gestion.ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/DevMVC")
public class DevloppeurControllerMVC {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;


    //  lister les tickets attribués au développeur
    @GetMapping("/affectedTickets/{idu}")
    public List<Ticket> getTicketOfDev(@PathVariable Long idu){
        return userRepository.findById(idu).get().getAffectedTickets();
    }
    // mettre à jour le statut d’un ticket (en cours de traitement, résolu)
    @PutMapping("Resoulu/{id}")
    public void updateAttr (@PathVariable Long id) {
        Ticket t = ticketRepository.findById(id).get();
        t.setStatut("résolu");
        ticketRepository.save(t);
    }

    // afficher Tickets
    @GetMapping("Ticket/{id}")
    public Ticket getTickets(@PathVariable Long id){
        return ticketRepository.findById(id).get();
    }

}
