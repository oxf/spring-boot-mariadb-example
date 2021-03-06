package com.example.demo.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product")
public @Data class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public String productName;
    @Column
    public Double price;
    @Column
    public Boolean isForAdult;

    public Product() {}
}
