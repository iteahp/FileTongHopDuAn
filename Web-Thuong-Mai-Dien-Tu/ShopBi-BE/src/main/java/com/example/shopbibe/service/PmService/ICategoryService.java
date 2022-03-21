package com.example.shopbibe.service.PmService;

import com.example.shopbibe.model.Category;
import com.example.shopbibe.model.Product;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();

    Category save(Category category);

    void delete(long id);

    Category findById(long id);
}
