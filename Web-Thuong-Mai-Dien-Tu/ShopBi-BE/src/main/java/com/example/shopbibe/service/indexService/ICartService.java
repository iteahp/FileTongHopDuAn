package com.example.shopbibe.service.indexService;

import com.example.shopbibe.dto.request.CartForm;
import com.example.shopbibe.model.Cart;

public interface ICartService {
    void saveCart(CartForm cartForm);
    void saveCart(Cart cart);

}
