package com.example.shopbibe.service.PmService;

import com.example.shopbibe.model.Product;
import com.example.shopbibe.model.Promotion;

import java.util.List;

public interface IPromotionService {
    List<Promotion> findAll();

    Promotion save(Promotion promotion);

    void delete(long id);

    Promotion findById(long id);
}
