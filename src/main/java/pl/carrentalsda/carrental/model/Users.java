package pl.carrentalsda.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {

    @Id                                               // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.AUTO)   // AUTO_INCREMENT
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String city;
    private String street;
    private String password;

    public Users(String firstname, String lastname, String email, int age, String city, String street, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.city = city;
        this.street = street;
        this.password = password;
    }

    // RELACJA N:M
    @ManyToMany(
            cascade = CascadeType.ALL,                          // pełna rekursywność
            fetch = FetchType.EAGER                             // zachłanne pobieranie rekordów
    )
    @JoinTable(
            name = "users_role",                                 // nazwa tabelki
            joinColumns = @JoinColumn(name = "users_id"),        // klucz uzytkownika
            inverseJoinColumns = @JoinColumn(name = "role_id")   // klucz roli
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        this.roles.add(role);
    }
}
