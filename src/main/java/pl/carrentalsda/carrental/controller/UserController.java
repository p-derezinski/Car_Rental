package pl.carrentalsda.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // wejście na stronę rejestracji
    @GetMapping("/register")
    public String register(Model model){
//        model.addAttribute("user", new UserDto());
        return "register";
    }

    // wejście na stronę logowania
    @GetMapping("/login")
    public String login(Model model){
//        model.addAttribute("user", new UserDto());
        return "login";
    }

    // wejście na stronę kontakt
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

}
