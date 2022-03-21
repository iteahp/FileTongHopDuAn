package com.example.casemd4.service;

import com.example.casemd4.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    public Page<Movie> findAll(Pageable pageable);
    public void save(Movie movie);
    public void delete(int id);
    public Movie findById(int id);
    List<Movie> findAllByCategoryIdCategory(int idCategory);
    Page<Movie> findAllByCategoryIdCategoryPage( Pageable pageable,int idCategory);
//    public Page<Movie> findByName(Pageable pageable, String name);
    void likeUp(int id);
    Page<Movie> findAllByNameContaining(Pageable pageable,String nameFind);
    void viewUp(int id);
    List<Movie> showTop5Comment();
    public Page<Movie> findByName(Pageable pageable, String name);
}
