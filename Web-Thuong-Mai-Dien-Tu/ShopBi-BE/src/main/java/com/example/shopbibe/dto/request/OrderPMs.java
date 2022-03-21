package com.example.shopbibe.dto.request;

import com.example.shopbibe.model.CartDetail;
import com.example.shopbibe.model.OrderDetail;
import com.example.shopbibe.model.Orders;

import java.util.List;

public class OrderPMs {
    private String usernameSaler;
    private String usernameBuyer;
    private String address_ship;
    private double billTotal;
    private List<CartDetail> cartDetails;

    public OrderPMs() {
    }

    public OrderPMs(String usernameSaler, String usernameBuyer, String address_ship, double billTotal, List<CartDetail> cartDetails) {
        this.usernameSaler = usernameSaler;
        this.usernameBuyer = usernameBuyer;
        this.address_ship = address_ship;
        this.billTotal = billTotal;
        this.cartDetails = cartDetails;
    }

    public String getUsernameSaler() {
        return usernameSaler;
    }

    public void setUsernameSaler(String usernameSaler) {
        this.usernameSaler = usernameSaler;
    }

    public String getUsernameBuyer() {
        return usernameBuyer;
    }

    public void setUsernameBuyer(String usernameBuyer) {
        this.usernameBuyer = usernameBuyer;
    }

    public String getAddress_ship() {
        return address_ship;
    }

    public void setAddress_ship(String address_ship) {
        this.address_ship = address_ship;
    }

    public double getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(double billTotal) {
        this.billTotal = billTotal;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
