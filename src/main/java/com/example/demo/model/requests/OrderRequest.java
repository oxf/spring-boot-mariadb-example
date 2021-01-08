package com.example.demo.model.requests;

import lombok.*;

public @Data class OrderRequest {
    public int userId;
    public int productId;
    public double price;
}
