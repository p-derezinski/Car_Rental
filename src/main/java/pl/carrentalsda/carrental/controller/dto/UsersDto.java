package pl.carrentalsda.carrental.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsersDto {

    @NotBlank(message = "Insert your first name")
    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private String city;
    private String street;
    private String password;

    // TODO - uzupełnić walidację

}
