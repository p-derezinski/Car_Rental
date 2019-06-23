package pl.carrentalsda.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.carrentalsda.carrental.controller.dto.UsersDto;
import pl.carrentalsda.carrental.model.Users;
import pl.carrentalsda.carrental.repository.UsersRepository;

@Service
public class UsersService {

    UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void saveUser(UsersDto usersDto){
        Users user =
                new Users(usersDto.getFirstname(),
                        usersDto.getLastname(),
                        usersDto.getEmail(),
                        usersDto.getAge(),
                        usersDto.getCity(),
                        usersDto.getStreet(),
                        usersDto.getPassword());
        // zapis do bazy danych
        System.out.println(user);
        usersRepository.save(user);
    }
}
