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

    public List<Cars> getAll() {
        return carsRepository.findAll();
    }

    public List<Cars> getAllByBranch(String branch) {
        return carsRepository.findAllByBranch(branch);
    }

    public Cars getFirstById(Long id) {
        return carsRepository.findFirstById(id);
    }

    public List<Cars> getAllByBrandAndYearsBetween(String brand, int yearFrom, int yearTo) {
        if (brand.equals("empty")) {
            return carsRepository.findAllByYearBetween(yearFrom, yearTo);
        }
        return carsRepository.findAllByBrandAndYearBetween(brand, yearFrom, yearTo);
    }
}
