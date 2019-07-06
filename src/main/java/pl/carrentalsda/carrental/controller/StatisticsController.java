package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.model.Reservation;
import pl.carrentalsda.carrental.model.Users;
import pl.carrentalsda.carrental.service.CarsService;
import pl.carrentalsda.carrental.service.ReservationService;
import pl.carrentalsda.carrental.service.UsersService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StatisticsController {

    // pola do wstrzyknięcia
    CarsService carsService;
    UsersService usersService;
    ReservationService reservationService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public StatisticsController(CarsService carsService, UsersService usersService, ReservationService reservationService) {
        this.carsService = carsService;
        this.usersService = usersService;
        this.reservationService = reservationService;
    }

    // wejście na stronę statystyk
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

    // wejście na stronę z formularzem do wyświetlania statystyk
    @GetMapping("/statisticsForm")
    public String showStatisticsForm(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        return "statisticsForm";
    }

    // obsługa wysłanego formularza do wyświetlania statystyk
    @PostMapping("/statisticsSearch")
    public String showStatisticsSearch(Model model, Authentication auth,
                                       @RequestParam(name = "brand", defaultValue = "empty") String brand,
                                       @RequestParam(name = "yearFrom", defaultValue = "1900") int yearFrom,
                                       @RequestParam(name = "yearTo", defaultValue = "2099") int yearTo) {
        model.addAttribute("auth", auth);

        if (yearFrom <= yearTo) {
            List<Cars> listOfSelectedCars = carsService.getAllCardByBrandAndYearsBetween(brand, yearFrom, yearTo);
            int numberOfCars = listOfSelectedCars.size();
            model.addAttribute("numberOfCars", numberOfCars);
        } else {
            String numberOfCars = "Invalid search parameters";
            model.addAttribute("numberOfCars", numberOfCars);
        }

        return "statisticsForm";
    }
}
