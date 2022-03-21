package com.example.shopbibe.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private  Product product;
    private double quantity;

}
