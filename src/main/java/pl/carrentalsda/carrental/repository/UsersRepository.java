package pl.carrentalsda.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.carrentalsda.carrental.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
}
