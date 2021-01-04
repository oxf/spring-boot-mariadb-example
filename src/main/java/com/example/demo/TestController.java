package com.example.demo;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("users")
    public Iterable<User> getUsers(){
        try {
            return usersRepository.findAll();
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return null;
        }
    }

    @GetMapping("users/{id}")
    public User getUserById(@PathVariable int id){
        try {
            var value = usersRepository.findById(id);
            return value.orElse(null);
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return null;
        }
    }

    @PostMapping("users")
    public boolean postUser(@RequestBody User user){
        try {
            if(user.id != null) {
                System.out.println("can't save entity with existing id, use update method instead!");
                return false;
            }
            usersRepository.save(user);
            return true;
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return false;
        }
    }

    @PutMapping("users/{id}")
    public boolean updateUser(@PathVariable int id, @RequestBody User user){
        try {
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
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return false;
        }
    }

    @DeleteMapping("users/{id}")
    public boolean deleteUser(@PathVariable int id){
        try {
            var userToDelete = usersRepository.findById(id);
            if(userToDelete.isPresent()) {
                usersRepository.delete(userToDelete.get());
                return true;
            } else {
                System.out.println("user with id "+id+" not found");
                return false;
            }
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return false;
        }
    }
}
