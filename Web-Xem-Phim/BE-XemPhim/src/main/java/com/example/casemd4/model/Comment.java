package com.example.casemd4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;
    @ManyToOne
    private Movie movie;

    private String comment;
    @ManyToOne
    private Users users;
    
}
