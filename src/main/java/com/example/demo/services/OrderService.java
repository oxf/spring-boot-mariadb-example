package com.example.demo.services;

import com.example.demo.model.entities.Order;
import com.example.demo.model.requests.OrderRequest;
import com.example.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public ProductService productService;

    public Boolean createOrder(OrderRequest orderRequest) {
        //check user and product exist
        var user = userService.getUserById(orderRequest.userId);
        if(user == null) {
            System.out.println("User with id "+orderRequest.userId+" was not found");
            return false;
        }
        var product = productService.getProductById(orderRequest.productId);
        if(product == null) {
            System.out.println("Product with id "+orderRequest.productId+" was not found");
            return false;
        }
        //check if user is adult and product is for adult
        if(product.isForAdult) {
            if(!user.isAdult) {
                System.out.println("Product with id "+orderRequest.productId+" is for adults only!");
                return false;
            }
        }
        //check if user has enough money
        if(user.pocket < orderRequest.price) {
            System.out.println("User with id "+user.id +" does not have enough money in his pocket, current balance: "+user.pocket+" needed: "+orderRequest.price);
            return false;
        }
        //count number of items
        int totalAmountRounded = (int) Math.floor(orderRequest.price / product.price);

        var orderToCreate = new Order(totalAmountRounded, product, user);
        //save order
        orderRepository.save(orderToCreate);
        //edit pocket
        user.pocket -= (totalAmountRounded * product.price);
        userService.updateUser(user.id, user);
        return true;
    }

    public Iterable<Order> getOrdersByProductId(Integer productId) {
        return orderRepository.findByProductId(productId);
    }

    public Iterable<Order> getOrdersByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
    }
}
