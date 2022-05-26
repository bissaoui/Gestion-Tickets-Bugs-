package gestion.ticket.controllers;

import gestion.ticket.models.Role;
import gestion.ticket.models.User;
import gestion.ticket.repository.RoleRepository;
import gestion.ticket.repository.UserRepository;
import gestion.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        if(u==null)
            return "connexion";
        else {
            return "redirect:/";
        }

    }
    @GetMapping("/profile")
    public String profile(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        m.addAttribute("user", u);
        return "profile";
    }
    @GetMapping("/inscription")
    public String inscription(Model m) {
        m.addAttribute("user", new User());
        return "inscription";
    }
    @GetMapping("/error")
    public String error(Model m) {
        m.addAttribute("user", new User());
        return "error";
    }
    @PostMapping("/inscription")
    public String inscription(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors())
            return "inscription";
        List<Role> list = new ArrayList<>();
        list.add(roleRepository.findRoleByName("USER"));
        user.setRoles(list);
        userService.saveUser(user);
        return "redirect:login";
    }

    @GetMapping
    public String home(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findUserByUsername(auth.getName());
        m.addAttribute("user", u);
        return "home";
    }

}
