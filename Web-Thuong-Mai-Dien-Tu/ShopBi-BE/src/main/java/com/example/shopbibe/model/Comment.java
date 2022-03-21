package com.example.shopbibe.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String content;
    @ManyToOne
    private User userComment;
    @ManyToOne
    private Product product;
}
