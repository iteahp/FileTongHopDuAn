package com.example.casemd4.controller;

import com.example.casemd4.model.Category;
import com.example.casemd4.model.Comment;
import com.example.casemd4.model.Movie;
import com.example.casemd4.model.query.CommentCount;
import com.example.casemd4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.te.CalendarData_te_IN;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/submovie")
public class MovieSubController {
    @Autowired
    IMovieService movieService;
    @Autowired
    ICommentService commentService;
    @Autowired
    ICategoryService categoryService;
    @GetMapping("/showAll")
    public Page<Movie> showAll(@RequestParam(defaultValue = "0") int page,@RequestParam String option,@RequestParam(defaultValue = "6")int size){
        return movieService.findAll(PageRequest.of(page,size, Sort.by(option).descending()));
    }
    @GetMapping("/find")
    public Page<Movie> findByName(@RequestParam(defaultValue = "0") int page,@RequestParam String nameFind){
        return movieService.findAllByNameContaining(PageRequest.of(page,12),nameFind);
    }
    @GetMapping("/{id}")
    public Movie findByIdPath(@PathVariable int id){
        return movieService.findById(id);
    }

    @GetMapping("/comment/{id}")
    public List<Comment> showAllCommentById(@PathVariable int id){
        return commentService.findCommentsByMovie_Id(id);
    }

    @GetMapping("/category/{idCategory}")
    public List<Movie> showAllByIdCategory(@PathVariable int idCategory){
        return movieService.findAllByCategoryIdCategory(idCategory);
    }

    @GetMapping("/movie/{idMovie}")
    public List<Movie> showAllByIdMovie(@PathVariable int idMovie){
        int idCategory = movieService.findById(idMovie).getCategory().getIdCategory();
        return movieService.findAllByCategoryIdCategory(idCategory);
    }

    @GetMapping("/category")
        public List<Category> showAllCategory(){
         return categoryService.findAll();
    }

    @GetMapping("/category/{idCategory}/{page}")
    public Page<Movie> showPageByCategory(@PathVariable int idCategory,@PathVariable int page){
        return movieService.findAllByCategoryIdCategoryPage(PageRequest.of(page,4),idCategory);
    }

    @PostMapping("/category/{id}")
    public Category findCategoryById(@PathVariable int id){
        return categoryService.findById(id);
    }

    @GetMapping("/likeUp/{id}")
    public void MovieLikeUp(@PathVariable int id){
        movieService.likeUp(id);
    }

    @PostMapping("/comment")
    public Comment commentMovie(@RequestBody Comment comment){
        return commentService.save(comment);
    }

    @GetMapping("/viewUp/{id}")
    public void MovieViewUp(@PathVariable int id){
        movieService.viewUp(id);
    }
    @GetMapping("/topcomment")
    public List<Movie> topComment() {
        return movieService.showTop5Comment();
    }
}
