package com.example.casemd4.controller;

import com.example.casemd4.model.Category;
import com.example.casemd4.model.Movie;
import com.example.casemd4.service.ICategoryService;
import com.example.casemd4.service.ICommentService;
import com.example.casemd4.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    IMovieService movieService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    ICommentService commentService;

    @GetMapping
    public ResponseEntity<Page<Movie>> movie(){
        return new ResponseEntity<>(movieService.findAll(PageRequest.of(0,6)), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> category() {
        return new ResponseEntity(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody Movie movie){
        movieService.save(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable int id){
        return new ResponseEntity(movieService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        movieService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> edit(@RequestBody Movie movie){
        movieService.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Page<Movie>> search(@PathVariable String name) {
        return new ResponseEntity(movieService.findByName(PageRequest.of(0,6), name), HttpStatus.OK);
    }
}
