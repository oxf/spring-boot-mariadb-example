package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue
    public Integer id;
    @Column
    public String name;
    @Column
    public Boolean isActive;
    @Column
    public Boolean isAdult;
    @Column
    public Double pocket;
}
