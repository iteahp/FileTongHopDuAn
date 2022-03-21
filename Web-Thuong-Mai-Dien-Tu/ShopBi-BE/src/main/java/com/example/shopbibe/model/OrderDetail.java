package com.example.shopbibe.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Orders orders;
    @ManyToOne
    private Product product;
    private double quantity;

    public OrderDetail(Orders orders, Product product, double quantity) {
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderDetail() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
