package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.carrentalsda.carrental.controller.dto.UsersDto;
import pl.carrentalsda.carrental.model.Reservation;
import pl.carrentalsda.carrental.model.Users;
import pl.carrentalsda.carrental.service.ReservationService;
import pl.carrentalsda.carrental.service.UsersService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    // pole do wstrzykniecia
    UsersService usersService;
    ReservationService reservationService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public UserController(UsersService usersService, ReservationService reservationService) {
        this.usersService = usersService;
        this.reservationService = reservationService;
    }

    // wejście na stronę rejestracji
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("users", new UsersDto());
        return "register";
    }

    // obsługa wysłanego formularza
    @PostMapping("/register")
    public String register(@ModelAttribute("users") @Valid UsersDto usersDto,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "register";
        }
        usersService.saveUser(usersDto);
        return "redirect:/";
    }

    // wejście na stronę logowania
    @GetMapping("/login")
    public String login(Model model){
//        model.addAttribute("user", new UserDto());
        return "login";
    }

    // błędne logowanie
    @GetMapping("/errorLogin")
    public String errorLogin(Model model) {
        String error = "error";
        model.addAttribute("error", error);
        return "login";
    }

    // wejście na stronę kontakt
    @GetMapping("/contact")
    public String contact(Model model, Authentication auth){
        model.addAttribute("auth", auth);
        return "contact";
    }

    // wejście na stronę klienta
    @GetMapping("/clientPage")
    public String clientPage(Model model, Authentication auth){
        model.addAttribute("auth", auth);

        String email = ((UserDetails)auth.getPrincipal()).getUsername();
        Users loggedUser = usersService.getFirstUserByEmail(email);
        model.addAttribute("loggedUser", loggedUser);

        List<Reservation> listOfReservations = reservationService.getAllReservationsByUser(loggedUser);
        model.addAttribute("listOfReservations", listOfReservations);

        return "client";
    }
}
