package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public int amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    public Product product;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnoreProperties("orders")
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, nullable = false)
    public User user;

    public Order() {}

    public Order(int amount, Product product, User user) {
        this.amount = amount;
        this.product = product;
        this.user = user;
    }
}
