package gestion.ticket.controllers;


import gestion.ticket.models.Ticket;
import gestion.ticket.models.User;
import gestion.ticket.repository.TicketRepository;
import gestion.ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // liste de tout les utilisateur
    @GetMapping("/User")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    // liste de tout les bugs
    @GetMapping("/Tickets")
    public List<Ticket> AllTickets(){
        return ticketRepository.findAll();
    }

    // liste des bugs non attribués,
    @GetMapping("/Tickets/attrFalse")
    public List<Ticket> AllTicketsno(){
        return ticketRepository.getTicketsByAttrIsFalse();
    }

    // attribuer un ticket à un développeur
    @GetMapping("/affecter/{idu}/{idt}")
    public void attribuer(@PathVariable("idu") Long idu, @PathVariable("idt") Long idt){

        User u= userRepository.findById(idu).get();
        Ticket t = ticketRepository.findById(idt).get();
        u.getAffectedTickets().add(t);
        t.setAttr(true);
        t.setStatut("attribuer");
        userRepository.save(u) ;
        ticketRepository.save(t);

    }

    // supprimer Ticket
    @DeleteMapping("Ticket/{id}")
    public String delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "hello";
    }

}
