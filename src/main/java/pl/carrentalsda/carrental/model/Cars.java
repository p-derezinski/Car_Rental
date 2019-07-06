package pl.carrentalsda.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String branch;
    private int status;
    private int price;

    private Cars(String brand,
                 String model,
                 String type,
                 int year,
                 String color,
                 int mileage,
                 String branch,
                 int status,
                 int price) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.branch = branch;
        this.status = status;
        this.price = price;
    }
}
