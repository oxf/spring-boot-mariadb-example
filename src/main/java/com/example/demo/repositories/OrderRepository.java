package com.example.demo.repositories;

import com.example.demo.model.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    Iterable<Order> findByProductId(int productId);

    Iterable<Order> findByUserId(Integer userId);
}
