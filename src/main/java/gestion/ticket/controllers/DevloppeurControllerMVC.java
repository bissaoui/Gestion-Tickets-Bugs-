package gestion.ticket.controllers;

import gestion.ticket.models.Ticket;
import gestion.ticket.models.User;
import gestion.ticket.repository.TicketRepository;
import gestion.ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/affectedTickets")
    public String tickets(Model m)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        long id= u.getId();
        m.addAttribute("tickets", userRepository.findById(id).get().getAffectedTickets());
        return "developpeur/Tickets";
    }

    // mettre à jour le statut d’un ticket (en cours de traitement, résolu)
    @GetMapping("/Resoulu/{id}")
    public String updateAttr (@PathVariable Long id) {
        Ticket t = ticketRepository.findById(id).get();
        t.setStatut("résolu");
        ticketRepository.save(t);
        return "redirect:/DevMVC/affectedTickets";
    }

    // afficher Tickets
    @GetMapping("/Ticket/{id}")
    public String Ticket(Model m, @PathVariable long id) {
        m.addAttribute("ticket", ticketRepository.findById(id).get());
        return "developpeur/ticket";
    }
}
