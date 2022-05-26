package gestion.ticket.repository;

import gestion.ticket.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByName(String Name);

}
