package com.example.shopbibe.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String link;
    @ManyToOne
    private Product product;

    public Img() {
    }

    public Img(String link, Product product) {
        this.link = link;
        this.product = product;
    }
}
