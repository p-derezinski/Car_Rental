package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.model.Role;
import pl.carrentalsda.carrental.model.Users;
import pl.carrentalsda.carrental.service.CarsService;
import pl.carrentalsda.carrental.service.ReservationService;
import pl.carrentalsda.carrental.service.UsersService;

import java.util.List;

@Controller
public class CarsController {

    // pola do wstrzyknięcia
    CarsService carsService;
    UsersService usersService;
    ReservationService reservationService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public CarsController(CarsService carsService, UsersService usersService, ReservationService reservationService) {
        this.carsService = carsService;
        this.usersService = usersService;
        this.reservationService = reservationService;
    }

    // wejście na stronę startową
    @GetMapping("/")
    public String getAllCars(Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        List<Cars> listOfCars = carsService.getAll();
        model.addAttribute("listOfCars", listOfCars);

        isItAnEmployee(model, auth);

        return "index";
    }

    @GetMapping("/krakow")
    public String getKrakowCars(Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        List<Cars> listOfKrakowCars = carsService.getAllByBranch("krakow");
        model.addAttribute("listOfCars", listOfKrakowCars);

        isItAnEmployee(model, auth);

        return "index";
    }

    @GetMapping("/poznan")
    public String getPoznanCars(Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        List<Cars> listOfPoznanCars = carsService.getAllByBranch("poznan");
        model.addAttribute("listOfCars", listOfPoznanCars);

        isItAnEmployee(model, auth);

        return "index";
    }

    @GetMapping("/gdansk")
    public String getGdanskCars(Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        List<Cars> listOfGdanskCars = carsService.getAllByBranch("gdansk");
        model.addAttribute("listOfCars", listOfGdanskCars);

        isItAnEmployee(model, auth);

        return "index";
    }

    @GetMapping("/warszawa")
    public String getWarszawaCars(Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        List<Cars> listOfWarszawaCars = carsService.getAllByBranch("warszawa");
        model.addAttribute("listOfCars", listOfWarszawaCars);

        isItAnEmployee(model, auth);

        return "index";
    }

    private void isItAnEmployee(Model model, Authentication auth) {
        if (auth != null) {
            String email = ((UserDetails) auth.getPrincipal()).getUsername();
            Users loggedUser = usersService.getFirstUserByEmail(email);
            Role userRole = usersService.getRole(2L);
            if (loggedUser.getRoles().contains(userRole)) {
                model.addAttribute("employee", true);
            }
        }
    }
}
