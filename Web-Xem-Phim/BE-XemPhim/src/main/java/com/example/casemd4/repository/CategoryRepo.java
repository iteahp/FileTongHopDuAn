package com.example.casemd4.repository;

import com.example.casemd4.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



public interface CategoryRepo extends PagingAndSortingRepository<Category, Integer> {
}
