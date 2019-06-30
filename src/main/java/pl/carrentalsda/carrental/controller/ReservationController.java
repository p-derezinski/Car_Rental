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
import java.util.Arrays;

@Controller
public class ReservationController {

    // pole do wstrzykniecia
    ReservationService reservationService;
    CarsService carsService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public ReservationController(ReservationService reservationService, CarsService carsService) {
        this.reservationService = reservationService;
        this.carsService = carsService;
    }

    @GetMapping("/reservation/{id}")
    public String processReservation(@PathVariable("id") Long id, Model model, Authentication auth){
        model.addAttribute("auth", auth);
//        model.addAttribute("post", new PostDto());
//        model.addAttribute("categories", Arrays.asList(CategoryEnum.values()));

        Cars carToView = carsService.getFirstCarById(id);
        model.addAttribute("carToView", carToView);
        return "reservation";
    }

    // obsługa kliknięcia potwierdzenia rezerwacji samochodu
    @PostMapping("/reservation")
    public String processReservation(@ModelAttribute("carConfirmed") @Valid Cars car,
                                     BindingResult bindingResult,
                                     Authentication auth,
                                     Model model) {
        String email = ((UserDetails)auth.getPrincipal()).getUsername();
        Long car_id = car.getId();
        reservationService.createReservation(email, car_id);
        car.setStatus(1);
        reservationService.updateCarInRepository(car);
        return "redirect:/";
    }

}
