package pl.carrentalsda.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.carrentalsda.carrental.model.Reservation;
import pl.carrentalsda.carrental.model.Users;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findAllByUser(Users user);

}
