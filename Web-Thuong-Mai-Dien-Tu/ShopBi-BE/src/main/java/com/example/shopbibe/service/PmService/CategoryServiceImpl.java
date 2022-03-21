package com.example.shopbibe.service.PmService;

import com.example.shopbibe.model.Category;
import com.example.shopbibe.repository.PmRepo.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).get();
    }
}
