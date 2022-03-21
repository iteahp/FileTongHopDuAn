package com.example.shopbibe.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;
    private double quantity;
    private double quantitySale; // so luong sp da ban
    private double quantityMax;// so luong toi da co the ban
    private double quantityMin;// so luong toi thieu co the ban
    private double priceSale;// gia sau khuyen mai
    private String coverPhoto;
    private long status ;
    private Date createAt;
    private Date modifyAt;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;
    @ManyToOne
    private Promotion promotion;

}
