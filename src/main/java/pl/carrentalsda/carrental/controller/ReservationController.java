package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    // wejście na stronę wyświetlającą wybrany samochód
    @GetMapping("/reservation/{id}")
    public String processReservation(@PathVariable("id") Long id, Model model, Authentication auth){
        model.addAttribute("auth", auth);

        Cars carToView = carsService.getFirstById(id);
        model.addAttribute("carToView", carToView);
        return "reservation";
    }

    // obsługa kliknięcia potwierdzenia rezerwacji samochodu
    @PostMapping("/reservation")
    public String processReservation(@ModelAttribute("carToView") @Valid Cars car,
                                     BindingResult bindingResult,
                                     Authentication auth,
                                     Model model) {
        String email = ((UserDetails)auth.getPrincipal()).getUsername();
        Long car_id = car.getId();
        reservationService.createReservation(email, car_id);
        Cars carToUpdate = carsService.getFirstById(car_id);
        carToUpdate.setStatus(1);
        reservationService.updateCarInRepository(carToUpdate);
        return "redirect:/";
    }

    // obsługa zwrotu auta przez pracownika
    @PostMapping("/return/{id}")
    public String processReturn(@PathVariable("id") Long id, @ModelAttribute("cars") @Valid Cars car,
                                     BindingResult bindingResult,
                                     Authentication auth,
                                     Model model) {
        Cars carToUpdate = carsService.getFirstById(id);
        carToUpdate.setStatus(0);
        reservationService.updateCarInRepository(carToUpdate);
        return "redirect:/";
    }
}
