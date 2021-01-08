package com.example.demo.services;

import com.example.demo.model.DTO.OrderSummaryDTO;
import com.example.demo.model.DTO.ProductSummaryDTO;
import com.example.demo.model.entities.Order;
import com.example.demo.model.entities.Product;
import com.example.demo.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SummaryService {
    @Autowired
    public OrderService orderService;
    @Autowired
    public UserService userService;
    @Autowired
    public ProductService productService;

    public Iterable<OrderSummaryDTO> getOrderSummary() {
        var productList = productService.getAllProducts();
        List<OrderSummaryDTO> orderSummaryDTOList = new LinkedList<OrderSummaryDTO>();
        productList.forEach(product -> {
            List<Order> ordersOfProduct = (List<Order>) orderService.getOrdersByProductId(product.id);
            //get sum of amount
            int totalAmount = ordersOfProduct.stream()
                    .mapToInt(x -> x.amount)
                    .sum();
            //get total price
            double totalSum = totalAmount * product.price;
            OrderSummaryDTO summaryToCreate = new OrderSummaryDTO(product, totalAmount, product.price, totalSum);
            orderSummaryDTOList.add(summaryToCreate);
        });
        return orderSummaryDTOList;
    }

    public Iterable<User> getUserSummary() {
        //return full user object
        return userService.getAllUsersOrders();
    }

    public Iterable<ProductSummaryDTO> getProductSummary() {
        //get product list
        var productList = (List<Product>) productService.getAllProducts();
        List<ProductSummaryDTO> productSummaryDTOList = new LinkedList<>();
        //create DTOs for all products
        productList.forEach(product -> {
            var ordersForProduct = (List<Order>) orderService.getOrdersByProductId(product.id);
            productSummaryDTOList.add(new ProductSummaryDTO(product, ordersForProduct));
        });
        return productSummaryDTOList;
    }
}
