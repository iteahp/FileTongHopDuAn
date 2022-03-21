package com.example.shopbibe.repository.indexRepo;

import com.example.shopbibe.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findAllByProductId(long id);

}
