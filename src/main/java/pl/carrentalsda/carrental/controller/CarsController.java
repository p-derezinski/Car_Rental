package pl.carrentalsda.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.service.CarsService;

import java.util.List;

@Controller
public class CarsController {
    CarsService carsService;

    @Autowired
    public CarsController(CarsService carsService) {
        this.carsService = carsService;
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
        return "statistics";
    }
}
