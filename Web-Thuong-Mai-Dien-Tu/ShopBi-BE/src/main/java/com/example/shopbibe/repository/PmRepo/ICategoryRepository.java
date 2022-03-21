package com.example.shopbibe.repository.PmRepo;

import com.example.shopbibe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
