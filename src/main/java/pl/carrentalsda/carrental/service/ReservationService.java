package pl.carrentalsda.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.model.Reservation;
import pl.carrentalsda.carrental.model.Users;
import pl.carrentalsda.carrental.repository.CarsRepository;
import pl.carrentalsda.carrental.repository.ReservationRepository;
import pl.carrentalsda.carrental.repository.UsersRepository;

import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    UsersRepository usersRepository;
    CarsRepository carsRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UsersRepository usersRepository, CarsRepository carsRepository) {
        this.reservationRepository = reservationRepository;
        this.usersRepository = usersRepository;
        this.carsRepository = carsRepository;
    }

    public void createReservation(String email, Long car_id) {
        Reservation reservation = new Reservation();
        Users user = usersRepository.findFirstByEmail(email);
        reservation.setUser(user);
        Cars car = carsRepository.findFirstById(car_id);
        reservation.setCars(car);
        reservationRepository.save(reservation);
    }

    public void updateCarInRepository(Cars car) {
        carsRepository.save(car);
    }

    public List<Reservation> getAllReservationsByUser(Users user) {
        return reservationRepository.findAllByUser(user);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
