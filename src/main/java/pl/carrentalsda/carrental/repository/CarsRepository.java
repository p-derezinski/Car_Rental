package pl.carrentalsda.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.carrentalsda.carrental.model.Cars;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {

    Cars findFirstById(Long id);

    List<Cars> findAllByYearBetween(int low, int high);

    List<Cars> findAllByBrand(String brand);

    List<Cars> findAllByBranch(String branch);

    List<Cars> findAllByBrandAndYearBetween(String brand, int low, int high);
}





