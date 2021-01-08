package com.example.demo.model.DTO;

import com.example.demo.model.entities.Order;
import com.example.demo.model.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
public @Data class ProductSummaryDTO {
    private Product product;
    private List<Order> orders;
}
