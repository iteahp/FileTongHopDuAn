package com.example.shopbibe.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CouponCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  Long code;

    @ManyToOne
    private Promotion promotion;


}
