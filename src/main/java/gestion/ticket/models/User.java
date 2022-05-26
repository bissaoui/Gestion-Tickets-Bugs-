package gestion.ticket.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String nom;
    private String prenom;
    @Column(unique=true)
    private  String username ;
    @Column(unique=true)
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> AffectedTickets;


    public User(Long id, String nom, String username, String prenom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
