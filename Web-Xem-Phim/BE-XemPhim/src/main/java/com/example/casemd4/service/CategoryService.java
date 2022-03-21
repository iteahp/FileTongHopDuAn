package com.example.casemd4.service;

import com.example.casemd4.model.Category;
import com.example.casemd4.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepo.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id).get();
    }

}
