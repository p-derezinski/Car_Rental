package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.service.CarsService;
import pl.carrentalsda.carrental.service.ReservationService;

import javax.validation.Valid;

@Controller
public class ReservationController {

    // pola do wstrzyknięcia
    ReservationService reservationService;
    CarsService carsService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public ReservationController(ReservationService reservationService, CarsService carsService) {
        this.reservationService = reservationService;
        this.carsService = carsService;
    }

    private static final int CAR_AVAILABLE = 0;
    private static final int CAR_BOOKED = 1;

    // wejście na stronę wyświetlającą wybrany samochód
    @GetMapping("/reservation/{id}")
    public String processReservation(@PathVariable("id") Long id, Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        Cars carToView = carsService.getFirstById(id);
        model.addAttribute("carToView", carToView);
        return "reservation";
    }

    // obsługa kliknięcia potwierdzenia rezerwacji samochodu
    @PostMapping("/reservation")
    public String processReservation(@ModelAttribute("carToView") @Valid Cars car,
                                     Authentication auth) {
        String email = ((UserDetails)auth.getPrincipal()).getUsername();
        Long carId = car.getId();
        reservationService.createReservation(email, carId);
        Cars carToUpdate = carsService.getFirstById(carId);
        carToUpdate.setStatus(CAR_BOOKED);
        reservationService.updateCarInRepository(carToUpdate);
        return "redirect:/";
    }

    // obsługa zwrotu auta przez pracownika
    @PostMapping("/return/{id}")
    public String processReturn(@PathVariable("id") Long id, @ModelAttribute("cars") @Valid Cars car) {
        Cars carToUpdate = carsService.getFirstById(id);
        carToUpdate.setStatus(CAR_AVAILABLE);
        reservationService.updateCarInRepository(carToUpdate);
        return "redirect:/";
    }
}
