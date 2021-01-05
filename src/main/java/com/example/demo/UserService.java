package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UsersRepository usersRepository;

    public void createNewUser(User user) {
        usersRepository.save(user);
    }

    public boolean updateUser(int id, User user) {
        var userToUpdate = usersRepository.findById(id);
        if(userToUpdate.isPresent()) {
            var userToSave = userToUpdate.get();
            userToSave.email = user.email;
            userToSave.username = user.username;
            usersRepository.save(userToSave);
            return true;
        } else {
            System.out.println("user with id "+id+" not found");
            return false;
        }
    }

    public User getUser(int id) {
        var value = usersRepository.findById(id);
        return value.orElse(null);
    }

    public Iterable<User> getAllUsers() {
        return usersRepository.findAll();
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


}
