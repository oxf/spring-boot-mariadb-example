package com.example.demo.services;

import com.example.demo.model.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ProductService {

    private final Pattern PATTERN_PRODUCT_NAME = Pattern.compile("^[a-zA-Z0-9 ]*$");

    @Autowired
    public ProductRepository productRepository;

    public boolean createProduct(Product product) {
        if(product.price != null) {
            if (product.price < 0) {
                System.out.println("Price cant be negative");
                return false;
            }
        } else {
            System.out.println("Price cant be null");
            return false;
        }
        if(product.isForAdult == null) {
            System.out.println("IsForAdults cant be null");
            return false;
        }
        if(product.productName == null) {
            System.out.println("ProductName cant be null");
            return false;
        } else {
            if(verifyProductName(product.productName)) {
                productRepository.save(product);
                return true;
            } else {
                System.out.println("ProductName is in wrong format");
                return false;
            }
        }
    }


    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        var value = productRepository.findById(productId);
        return value.orElse(null);
    }

    public boolean verifyProductName(String productName) {
        return PATTERN_PRODUCT_NAME.matcher(productName).matches();
    }

}
