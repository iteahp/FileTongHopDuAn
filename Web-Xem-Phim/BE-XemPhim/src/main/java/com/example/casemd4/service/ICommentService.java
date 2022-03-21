package com.example.casemd4.service;

import com.example.casemd4.model.Comment;
import com.example.casemd4.model.query.CommentCount;

import java.util.List;

public interface ICommentService {
    public List<Comment> findAll();
    List<Comment> findCommentsByMovie_Id(int id);
    Comment save(Comment comment);
    List<CommentCount> getListIdByCount();
}
