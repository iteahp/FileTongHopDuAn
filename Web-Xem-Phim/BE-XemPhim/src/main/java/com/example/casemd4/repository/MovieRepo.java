package com.example.casemd4.repository;

import com.example.casemd4.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MovieRepo extends PagingAndSortingRepository<Movie, Integer> {
//    Page<Movie> findAllByNameContaining(Pageable pageable, String name);
    Page<Movie> findAll(Pageable pageable);
    List<Movie> findAllByCategoryIdCategory(int idCategory);
    Page<Movie> findAllByCategoryIdCategory(Pageable pageable,int idCategory);
    Page<Movie> findAllByNameContaining(Pageable pageable,String nameFind);
}
