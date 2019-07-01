package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.carrentalsda.carrental.controller.dto.UsersDto;
import pl.carrentalsda.carrental.service.UsersService;

import javax.validation.Valid;

@Controller
public class UserController {

    // pole do wstrzykniecia
    UsersService usersService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
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

}
