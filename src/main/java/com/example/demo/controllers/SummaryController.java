package com.example.demo.controllers;

import com.example.demo.model.DTO.OrderSummaryDTO;
import com.example.demo.model.DTO.ProductSummaryDTO;
import com.example.demo.model.entities.User;
import com.example.demo.services.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummaryController {
    @Autowired
    public SummaryService summaryService;

    @GetMapping("summary/all")
    public ResponseEntity<Iterable<OrderSummaryDTO>> getOrderSummary() {
        try {
            return new ResponseEntity<Iterable<OrderSummaryDTO>>(summaryService.getOrderSummary(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception was thrown: "+e);
            return new ResponseEntity<Iterable<OrderSummaryDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("summary/product")
    public ResponseEntity<Iterable<ProductSummaryDTO>> getProductSummary() {
        try {
            return new ResponseEntity<Iterable<ProductSummaryDTO>>(summaryService.getProductSummary(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception was thrown: "+e);
            return new ResponseEntity<Iterable<ProductSummaryDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("summary/user")
    public ResponseEntity<Iterable<User>> getUserSummary() {
        try {
            return new ResponseEntity<Iterable<User>>(summaryService.getUserSummary(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Exception was thrown: "+e);
            return new ResponseEntity<Iterable<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
