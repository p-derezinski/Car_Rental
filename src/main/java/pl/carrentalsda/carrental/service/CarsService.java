package pl.carrentalsda.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.carrentalsda.carrental.model.Cars;
import pl.carrentalsda.carrental.repository.CarsRepository;

import java.util.List;

@Service
public class CarsService {
    CarsRepository carsRepository;
    @Autowired
    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public List<Cars> getAllCars() {
        return carsRepository.findAll();
    }

    public List<Cars> getAllCarsFromYearsBetween() {
        return carsRepository.findAllByYearBetween(2010, 2017);
    }

    public List<Cars> getAllCarsByBrand(String brand) {
        return carsRepository.findAllByBrand(brand);
    }

    public Cars getFirstCarById(Long id) {
        return carsRepository.findFirstById(id);
    }
}
