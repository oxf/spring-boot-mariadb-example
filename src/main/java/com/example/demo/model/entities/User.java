package com.example.demo.model.entities;

import com.example.demo.model.entities.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public @Data class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public String name;
    @Column
    public Boolean isActive;
    @Column
    public Boolean isAdult;
    @Column
    public Double pocket;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    public List<Order> orders;

    public User() {}
}
