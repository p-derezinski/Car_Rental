package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.carrentalsda.carrental.service.ReservationService;

import java.util.Arrays;

@Controller
public class ReservationController {

    // pole do wstrzykniecia
    ReservationService reservationService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String addPost(Model model, Authentication auth){
        model.addAttribute("auth", auth);
//        model.addAttribute("post", new PostDto());
//        model.addAttribute("categories", Arrays.asList(CategoryEnum.values()));
        return "reservation";
    }

    // TODO - dodać Get (?) i Post Mapping analogicznie jak addPost w PostController

}
