package ru.readles.readlesshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.readles.readlesshop.entity.CommentEntity;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    private Long id_comment;
    private Long user;
    private Long book;

    private String description;
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

}
