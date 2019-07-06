package pl.carrentalsda.carrental.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.carrentalsda.carrental.model.Cars;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars, Long> {
    List<Cars> findAllById(Long id);

    Cars findFirstById(Long id);

    List<Cars> findAllByYearBetween(int low, int high);

    List<Cars> findAllByBrand(String brand);

    List<Cars> findAllByBranch(String branch);
}





