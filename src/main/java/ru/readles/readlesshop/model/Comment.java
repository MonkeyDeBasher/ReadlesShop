package ru.readles.readlesshop.model;

import ru.readles.readlesshop.entity.CommentEntity;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private Long id_comment;
    private Long user;
    private Long book;

    private String description;
    public Comment(){

    }
    public static List<Comment> toModel(List<CommentEntity> entities) {
        List<Comment> commentModels = new ArrayList<>();
        for (CommentEntity entity : entities) {
            Comment model = new Comment();
            model.setId_comment(entity.getId_comment());
            model.setUser(entity.getUser().getId_user());
            model.setBook(entity.getBook().getId_book());
            model.setDescription(entity.getDescription());
            commentModels.add(model);
        }
        return commentModels;
    }

    public Long getId_comment() {
        return id_comment;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getBook() {
        return book;
    }

    public void setBook(Long book) {
        this.book = book;
    }

    public void setId_comment(Long id_comment) {
        this.id_comment = id_comment;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
