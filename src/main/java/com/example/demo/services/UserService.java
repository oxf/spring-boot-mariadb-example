package com.example.demo.services;

import com.example.demo.model.DTO.UserShortDTO;
import com.example.demo.model.User;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final Pattern PATTERN_USERNAME = Pattern.compile("^[A-Za-z0-9_-]*$");

    @Autowired
    public UsersRepository usersRepository;
    @Autowired
    public OrderRepository orderRepository;

    public boolean createNewUser(User user) {
        //verify pocket amount
        if(user.pocket < 0d) {
            System.out.println("Can't create user with negative pocket amount");
            return false;
        }
        //verify isActive
        if(user.isActive == null) {
            System.out.println("IsActive was not filled");
            return false;
        }
        //verify isAdult
        if(user.isAdult == null) {
            System.out.println("IsAdult was not filled");
            return false;
        }
        //verify username
        if (!verifyUsername(user.name)) {
            System.out.println("Wrong username format");
            return false;
        }
        usersRepository.save(user);
        return true;
    }

    public boolean updateUser(int id, User user) {
        var userToUpdate = usersRepository.findById(id);
        if (userToUpdate.isPresent()) {
            var userToSave = userToUpdate.get();
            //verify pocket amount
            if(user.pocket < 0d) {
                System.out.println("Can't create user with negative pocket amount");
                return false;
            } else {
                userToSave.pocket = user.pocket;
            }
            //verify isActive
            if(user.isActive == null) {
                System.out.println("IsActive was not filled");
                return false;
            } else {
                userToSave.isActive = user.isActive;
            }
            //verify isAdult
            if(user.isAdult == null) {
                System.out.println("IsAdult was not filled");
                return false;
            } else {
                userToSave.isAdult = user.isAdult;
            }
            //verify username
            if (verifyUsername(user.name)) {
                userToSave.name = user.name;
            } else {
                System.out.println("Wrong username format");
                return false;
            }
            usersRepository.save(userToSave);
            return true;
        } else {
            System.out.println("user with id " + id + " not found");
            return false;
        }
    }

    public User getUserOrders(int id) {
        return getUserById(id);
    }

    public User getUserById(int id) {
        var value = usersRepository.findById(id);
        return value.orElse(null);
    }

    public List<UserShortDTO> getAllUsers() {
        List<UserShortDTO> dtosList = new ArrayList<UserShortDTO>();
        usersRepository.findAll().forEach(x -> dtosList.add(new UserShortDTO(x.id, x.name, x.isActive, x.isAdult, x.pocket)));
        return dtosList;
    }

    public boolean deleteUser(int id) {
        var userToDelete = usersRepository.findById(id);
        if(userToDelete.isPresent()) {
            usersRepository.delete(userToDelete.get());
            return true;
        } else {
            System.out.println("user with id "+id+" not found");
            return false;
        }
    }

    private boolean verifyUsername(String username) {
        return PATTERN_USERNAME.matcher(username).matches();
    }

}
