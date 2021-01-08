package com.example.demo.controllers;

import com.example.demo.model.requests.OrderRequest;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping("buy")
    public ResponseEntity<Boolean> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            return new ResponseEntity<Boolean>(orderService.createOrder(orderRequest), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception was thrown: "+e);
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
