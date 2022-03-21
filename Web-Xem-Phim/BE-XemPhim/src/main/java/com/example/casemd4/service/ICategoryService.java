package com.example.casemd4.service;

import com.example.casemd4.model.Category;

import java.util.List;

public interface ICategoryService {
    public List<Category> findAll();
    public Category findById(int id);
}
