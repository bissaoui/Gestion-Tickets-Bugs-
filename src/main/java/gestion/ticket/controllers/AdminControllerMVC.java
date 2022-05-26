package gestion.ticket.controllers;


import gestion.ticket.models.Role;
import gestion.ticket.models.Ticket;
import gestion.ticket.models.User;
import gestion.ticket.repository.RoleRepository;
import gestion.ticket.repository.TicketRepository;
import gestion.ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/AdminMVC")
public class AdminControllerMVC {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private RoleRepository roleRepository;


    // liste de tout les utilisateur
    @GetMapping("/Users")
    public String users(Model m) {
        m.addAttribute("users", userRepository.findAll());
        return "admin/users";
    }


    //Find User By Id
    @GetMapping("/User/{id}")
    public String users(Model m,@PathVariable long id) {
        m.addAttribute("user", userRepository.findById(id).get());
        return "admin/users";
    }


    // liste de tout les bugs
    @GetMapping("/Tickets")
    public String AllTickets(Model m) {
        m.addAttribute("tickets", ticketRepository.findAll());
        return "admin/tickets";
    }


    // Find Ticket by ID
    @GetMapping("/Ticket/{id}")
    public String Ticket(Model m, @PathVariable long id) {
        m.addAttribute("ticket", ticketRepository.findById(id).get());
        return "admin/ticket";
    }


    // liste des bugs non attribués,
    @GetMapping("/Tickets/attrFalse")
    public String AllTicketsno(Model m) {
        m.addAttribute("tickets", ticketRepository.getTicketsByAttrIsFalse());
        return "admin/tickets";
    }

    @GetMapping("/Tickets/attr/{idt}")
    public String attribuer(Model m, @PathVariable("idt") Long idt) {
        Role R = roleRepository.findRoleByName("DEV");
        m.addAttribute("users", userRepository.findUserByRoles(R));
        m.addAttribute("idt",idt);
        return "admin/attribueTicket";
    }



    // attribuer un ticket à un développeur
    @GetMapping("/affecter/{idu}/{idt}")
    public String attribuer(@PathVariable("idu") Long idu, @PathVariable("idt") Long idt){
        User u= userRepository.findById(idu).get();
        Ticket t = ticketRepository.findById(idt).get();
        u.getAffectedTickets().add(t);
        t.setAttr(true);
        t.setStatut("attribuer");
        userRepository.save(u) ;
        ticketRepository.save(t);
        return "redirect:/AdminMVC/Tickets";
    }


    // supprimer Ticket
    @PostMapping("Ticket/d/{id}")
    public String delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/AdminMVC/Tickets";

    }

}
