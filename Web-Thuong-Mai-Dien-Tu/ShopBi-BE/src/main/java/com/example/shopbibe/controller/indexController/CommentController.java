package com.example.shopbibe.controller.indexController;

import com.example.shopbibe.model.Comment;
import com.example.shopbibe.model.User;
import com.example.shopbibe.service.IUserService;
import com.example.shopbibe.service.indexService.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    ICommentService iCommentService;
    @Autowired
    IUserService iUserService;
    // ham tra ve list comment theo productId duoc truyen sang
    @GetMapping("/{idProduct}")
    public List<Comment> getListComment(@PathVariable long idProduct){
        return  iCommentService.findAllByProductId(idProduct);
    }
    @PostMapping("")
    public void saveComment(@RequestBody Comment comment){
        iCommentService.save(comment);
    }
    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username){
        return iUserService.findByUsername(username).get();
    }
}
