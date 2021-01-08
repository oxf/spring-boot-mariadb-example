package com.example.demo.model.DTO;

import com.example.demo.model.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class OrderSummaryDTO {
    private Product product;
    private int amount;
    private double unitPrice;
    private double summaryPrice;
}
