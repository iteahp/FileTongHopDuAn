package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> findAllByProductId(long id);
    void save(Comment comment);
}
