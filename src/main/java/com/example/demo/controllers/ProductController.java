package com.example.demo.controllers;

import com.example.demo.model.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    public ProductService productService;

    @PostMapping("/products/")
    public ResponseEntity<Boolean> createProduct(@RequestBody Product product) {
        try {
            if(productService.createProduct(product)) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(true, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println("Exception was thrown: "+e);
            return new ResponseEntity<Boolean>(true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
