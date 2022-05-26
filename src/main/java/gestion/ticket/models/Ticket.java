package gestion.ticket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String description;
    private String urgence;
    private String envExe;
    private String logiciel;
    private String statut;
    private Boolean attr;

    @ManyToOne
    private User user;





}
