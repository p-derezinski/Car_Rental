package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.model.Reservation;
import pl.carrentalsda.carrental.model.Role;
import pl.carrentalsda.carrental.model.Users;
import pl.carrentalsda.carrental.service.CarsService;
import pl.carrentalsda.carrental.service.ReservationService;
import pl.carrentalsda.carrental.service.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarsController {

    CarsService carsService;
    UsersService usersService;
    ReservationService reservationService;

    @Autowired
    public CarsController(CarsService carsService, UsersService usersService, ReservationService reservationService) {
        this.carsService = carsService;
        this.usersService = usersService;
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String getAllCars(Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        List<Cars> listOfCars = carsService.getAllCars();
        model.addAttribute("listOfCars", listOfCars);

        if (auth != null) {
            String email = ((UserDetails)auth.getPrincipal()).getUsername();
            Users loggedUser = usersService.getFirstUserByEmail(email);
            Role userRole = usersService.getRole(2L);
            if (loggedUser.getRoles().contains(userRole)) {
                model.addAttribute("employee", true);
            }
        }

        return "index";
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model, Authentication auth) {
        model.addAttribute("auth", auth);

        List<Cars> listOfCars = carsService.getAllCars();
        int numberOfCars = listOfCars.size();
        model.addAttribute("numberOfCars", numberOfCars);

        List<Cars> listOfSelectedCars_2 = carsService.getAllCarsFromYearsBetween();
        int numberOfCars_2 = listOfSelectedCars_2.size();
        model.addAttribute("numberOfCars_2", numberOfCars_2);

        List<Cars> listOfSelectedCars_3 = carsService.getAllCarsByBrand("Audi");
        int numberOfCars_3 = listOfSelectedCars_3.size();
        model.addAttribute("numberOfCars_3", numberOfCars_3);

        List<Cars> listOfSelectedCars_4 = carsService.getAllCarsByBrand("Toyota");
        int numberOfCars_4 = listOfSelectedCars_4.size();
        model.addAttribute("numberOfCars_4", numberOfCars_4);

        List<Users> listOfUsers = usersService.getAllUsers();
        model.addAttribute("listOfUsers", listOfUsers);
        int numberOfUsers = listOfUsers.size();
        model.addAttribute("numberOfUsers", numberOfUsers);

        List<Reservation> listOfReservations = reservationService.getAllReservations();
        int numberOfReservations = listOfReservations.size();
        model.addAttribute("numberOfReservations", numberOfReservations);
        int totalIncome = 0;
        for (Reservation reservation : listOfReservations) {
            totalIncome += reservation.getCars().getPrice();
        }
        model.addAttribute("totalIncome", totalIncome);

        Map<Users, Integer> userAndNumberOfReservations = new HashMap<>();
        for (Users user : listOfUsers) {
            List<Reservation> listOfUserReservations = reservationService.getAllReservationsByUser(user);
            int numberOfUserReservations = listOfUserReservations.size();
            if (numberOfUserReservations > 0) {
                userAndNumberOfReservations.put(user, numberOfUserReservations);
            }
        }
        model.addAttribute("userAndNumberOfReservations", userAndNumberOfReservations);

        return "statistics";
    }
}
