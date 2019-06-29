package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.carrentalsda.carrental.controller.dto.UsersDto;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.model.Users;
import pl.carrentalsda.carrental.service.CarsService;
import pl.carrentalsda.carrental.service.UsersService;

import java.util.List;

@Controller
public class CarsController {

    CarsService carsService;
    UsersService usersService;

    @Autowired
    public CarsController(CarsService carsService, UsersService usersService) {
        this.carsService = carsService;
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String getAllCars(Model model) {
        List<Cars> listOfCars = carsService.getAllCars();
        model.addAttribute("listOfCars", listOfCars);
        return "index";
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {

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
//        model.addAttribute("users", new UsersDto());
        model.addAttribute("listOfUsers", listOfUsers);
        int numberOfUsers = listOfUsers.size();
        model.addAttribute("numberOfUsers", numberOfUsers);

        return "statistics";
    }
}
