package com.example.shopbibe.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class RateProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Product product;
    @OneToOne
    private Rate rate;

    public RateProduct(Orders orders, Product product, Rate rate) {
        this.orders = orders;
        this.product = product;
        this.rate = rate;
    }

    public RateProduct() {
    }

    public RateProduct(Long id, Orders orders, Product product, Rate rate) {
        this.id = id;
        this.orders = orders;
        this.product = product;
        this.rate = rate;
    }
}
