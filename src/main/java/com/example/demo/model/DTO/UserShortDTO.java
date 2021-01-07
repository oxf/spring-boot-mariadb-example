package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
public class UserShortDTO {
    private int id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;

    public UserShortDTO(Integer id, String name, Boolean isActive, Boolean isAdult, Double pocket) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.isAdult = isAdult;
        this.pocket = pocket;
    }
}
