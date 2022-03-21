package model;

import java.util.Date;

public class Order {
    private int id;
    private int accountId;
    private double totalPrice;
    private Date date;

    public Order() {
    }

    public Order(int id, int accountId, double totalPrice, Date date) {
        this.id = id;
        this.accountId = accountId;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Order(int id, int accountId) {
        this.id = id;
        this.accountId = accountId;
    }

    public Order(int id, int accountId, double totalPrice) {
        this.id = id;
        this.accountId = accountId;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
