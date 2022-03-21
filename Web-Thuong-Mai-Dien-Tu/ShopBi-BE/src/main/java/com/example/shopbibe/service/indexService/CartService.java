package com.example.shopbibe.service.indexService;

import com.example.shopbibe.dto.request.CartForm;
import com.example.shopbibe.model.Cart;
import com.example.shopbibe.model.CartDetail;
import com.example.shopbibe.repository.indexRepo.ICartDetailRepo;
import com.example.shopbibe.repository.indexRepo.ICartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    ICartRepo iCartRepo;
    @Autowired
    ICartDetailRepo iCartDetailRepo;

    @Override
    public void saveCart(CartForm cartForm) {
        Cart cart = cartForm.getCart();
        List<CartDetail> cartDetailList = cartForm.getCartDetailList();
        iCartRepo.save(cart);
        for (CartDetail cartDetail: cartDetailList
             ) {
            iCartDetailRepo.save(cartDetail);
        }
    }

    @Override
    public void saveCart(Cart cart) {
        iCartRepo.save(cart);
    }
}
