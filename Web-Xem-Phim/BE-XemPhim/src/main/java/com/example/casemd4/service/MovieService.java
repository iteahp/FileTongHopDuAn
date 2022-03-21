package com.example.casemd4.service;

import com.example.casemd4.model.Comment;
import com.example.casemd4.model.Movie;
import com.example.casemd4.model.query.CommentCount;
import com.example.casemd4.repository.CommentRepo;
import com.example.casemd4.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService implements IMovieService{

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    CommentRepo commentRepo;
    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return movieRepo.findAll(pageable);
    }

    @Override
    public void save(Movie movie) {
        movieRepo.save(movie);
    }

    @Override
    public void delete(int id) {
        movieRepo.deleteById(id);
    }

    @Override
    public Movie findById(int id) {
        return movieRepo.findById(id).get();
    }

    @Override
    public List<Movie> findAllByCategoryIdCategory(int idCategory) {
        return movieRepo.findAllByCategoryIdCategory(idCategory);
    }

    @Override
    public Page<Movie> findAllByCategoryIdCategoryPage(Pageable pageable, int idCategory) {
        return movieRepo.findAllByCategoryIdCategory(pageable,idCategory);
    }

    @Override
    public void likeUp(int id) {
        Movie movie= movieRepo.findById(id).get();
        movie.setLikeM(movie.getLikeM()+1);
        movieRepo.save(movie);
    }

    @Override
    public Page<Movie> findAllByNameContaining(Pageable pageable, String nameFind) {
        return movieRepo.findAllByNameContaining(pageable,nameFind);
    }

    @Override
    public void viewUp(int id) {
        Movie movie= movieRepo.findById(id).get();
        movie.setViewM(movie.getViewM()+1);
        movieRepo.save(movie);
    }

    @Override
    public List<Movie> showTop5Comment() {
       List<Movie> movieList = new ArrayList<>();
        for (CommentCount commentCount:  commentRepo.getListIdByCount()
             ) {
            movieList.add(movieRepo.findById(commentCount.getMovieId()).get());
        }
        return movieList;
    }

    @Override
    public Page<Movie> findByName(Pageable pageable, String name) {
        return movieRepo.findAllByNameContaining(pageable, name);
    }
}
