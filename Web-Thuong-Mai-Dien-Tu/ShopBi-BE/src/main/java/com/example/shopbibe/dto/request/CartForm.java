package com.example.shopbibe.dto.request;

import com.example.shopbibe.model.Cart;
import com.example.shopbibe.model.CartDetail;

import java.util.List;

public class CartForm {
    private Cart cart;
    private List<CartDetail> cartDetailList;

    public CartForm() {
    }
    public CartForm(Cart cart, List<CartDetail> cartDetailList) {
        this.cart = cart;
        this.cartDetailList = cartDetailList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }
}
