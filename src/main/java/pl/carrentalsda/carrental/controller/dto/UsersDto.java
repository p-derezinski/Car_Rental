package pl.carrentalsda.carrental.controller.dto;

import lombok.Data;

@Data
public class UsersDto {

    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String city;
    private String street;
    private String password;

}
