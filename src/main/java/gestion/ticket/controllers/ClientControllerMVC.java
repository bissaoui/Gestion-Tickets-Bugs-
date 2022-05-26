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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ClientMVC")
public class ClientControllerMVC {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/tickets")
    public String tickets(Model m)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        long id= u.getId();
        m.addAttribute("tickets", ticketRepository.getTicketByUser_Id(id));
        return "client/tickets";
    }
    @GetMapping("/Ticket/{id}")
    public String Ticket(Model m, @PathVariable long id) {
        m.addAttribute("ticket", ticketRepository.findById(id).get());
        return "client/ticket";
    }

    //Ajouter une tickets
    @GetMapping("/add")
    public String create(Model m) {
        m.addAttribute("ticket", new Ticket());
        return "client/CreateTicket";
    }

    // créer un ticket
    @PostMapping("/add")
    public String add(@ModelAttribute ("ticket") Ticket ticket) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        ticket.setUser(u);
        ticket.setAttr(false);
        ticket.setStatut("ouvert");
        ticketRepository.save(ticket);
        return "redirect:/ClientMVC/tickets";
    }
}
