package ru.readles.readlesshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.readles.readlesshop.entity.CommentEntity;
import ru.readles.readlesshop.entity.LibraryEntity;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Library {
    private Long id_lib;
    private Long user;
    private Long book;
    private String author;
    private String description;
    private String urlImg;
    public static List<Library> toModel(List<LibraryEntity> libraryEntity) {
        List<Library> libraryModels = new ArrayList<>();
        for (LibraryEntity entity : libraryEntity) {
            Library model = new Library();
            model.setUser(entity.getUser().getId_user());
            model.setBook(entity.getBook().getId_book());
            model.setAuthor(entity.getBook().getAuthor());
            model.setDescription(entity.getBook().getDescription());
            model.setUrlImg(entity.getBook().getUrlImg());
            libraryModels.add(model);
        }
        return libraryModels;
    }

}
