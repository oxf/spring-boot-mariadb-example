package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue
    public Integer id;
    @Column
    public String productName;
    @Column
    public int amount;
    @Column
    public double price;
}
