package gestion.ticket.repository;

import gestion.ticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> getTicketsByAttrIsFalse();

    List<Ticket> getTicketByUser_Id(Long id);
}
