package pl.carrentalsda.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private String type;
    private int year;
    private String color;
    private int mileage;
    private int status;
    private int price;

    private Cars(String brand,
                 String model,
                 String type,
                 int year,
                 String color,
                 int mileage,
                 int status,
                 int price) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.status = status;
        this.price = price;
    }


//    // RELACJA 1:N
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cars")
//    private List<Reservation> reservations = new ArrayList<>();
//
//    public void addReservation(Reservation reservation){
//        this.reservations.add(reservation);
//    }
}
