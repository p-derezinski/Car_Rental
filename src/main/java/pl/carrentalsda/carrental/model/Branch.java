package pl.carrentalsda.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.carrentalsda.carrental.model.enums.BranchEnum;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BranchEnum branchEnum;


//    // RELACJA 1:N
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "branch")
//    private List<Reservation> reservations = new ArrayList<>();
//
//    public void addReservation(Reservation reservation){
//        this.reservations.add(reservation);
//    }

}
