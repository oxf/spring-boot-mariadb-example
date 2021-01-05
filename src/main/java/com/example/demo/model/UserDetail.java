package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

public class UserDetail {
    User user;
    List<Order> orders;
}
