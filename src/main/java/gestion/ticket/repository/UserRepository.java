package gestion.ticket.repository;

import gestion.ticket.models.Role;
import gestion.ticket.models.Ticket;
import gestion.ticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUsername(String Username);
    List<User> findUserByRoles(Role Role);



}
