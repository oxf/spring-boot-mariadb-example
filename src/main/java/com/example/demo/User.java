package com.example.demo;

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
    public String username;
    @Column
    public String email;
}
