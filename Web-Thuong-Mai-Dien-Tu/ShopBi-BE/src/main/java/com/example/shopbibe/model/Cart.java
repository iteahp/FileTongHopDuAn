package com.example.shopbibe.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
