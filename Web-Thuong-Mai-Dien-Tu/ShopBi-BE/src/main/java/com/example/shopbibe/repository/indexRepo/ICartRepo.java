package com.example.shopbibe.repository.indexRepo;

import com.example.shopbibe.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepo extends JpaRepository<Cart,Long> {

}
