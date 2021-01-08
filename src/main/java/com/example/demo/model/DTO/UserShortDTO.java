package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data  class UserShortDTO {
    private int id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
}
