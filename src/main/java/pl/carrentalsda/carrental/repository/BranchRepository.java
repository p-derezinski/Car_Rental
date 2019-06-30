package pl.carrentalsda.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.carrentalsda.carrental.model.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {

    Branch findFirstById(Long id);

}
