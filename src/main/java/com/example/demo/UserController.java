package com.example.demo;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    public ResponseEntity<Iterable<User>> getUsers(){
        try {
            return new ResponseEntity<Iterable<User>>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return new ResponseEntity<Iterable<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        try {
            var value = userService.getUser(id);
            if(value == null) {
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            } else return new ResponseEntity<User>(value, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("users")
    public ResponseEntity<Boolean> postUser(@RequestBody User user){
        try {
            if(user.id != null) {
                System.out.println("can't save entity with existing id, use update method instead!");
                return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
            }
            userService.createNewUser(user);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("users/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable int id, @RequestBody User user){
        try {
            var value = userService.updateUser(id, user);
            if(value) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("exception was thrown: "+e);
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int id){
        try {
            var value = userService.deleteUser(id);
            if(value) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {

                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}