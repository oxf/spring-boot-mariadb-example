package com.example.demo.model.DTO;

import com.example.demo.model.entities.Order;
import com.example.demo.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
public @Data class UserSummaryDTO {
    private User user;
    private List<Order> orders;
}
