package pl.carrentalsda.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.carrentalsda.carrental.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
