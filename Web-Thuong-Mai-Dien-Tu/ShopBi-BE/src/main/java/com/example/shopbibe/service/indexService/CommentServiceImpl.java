package com.example.shopbibe.service.indexService;

import com.example.shopbibe.model.Comment;
import com.example.shopbibe.repository.indexRepo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    ICommentRepo iCommentRepo;

    @Override
    public List<Comment> findAllByProductId(long id) {
        return iCommentRepo.findAllByProductId(id);
    }

    @Override
    public void save(Comment comment) {
            iCommentRepo.save(comment);
    }
}
