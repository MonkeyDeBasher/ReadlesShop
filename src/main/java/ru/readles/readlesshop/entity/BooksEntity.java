package ru.readles.readlesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="Books")
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_book;

    private String title;

    private String author;

    private String description;

    private String category;

    private Integer price;

    private String urlImg;
    @JsonIgnore
    @OneToMany(mappedBy = "id_lib")
    private List<LibraryEntity> libraries;
    @JsonIgnore
    @OneToMany(mappedBy = "id_comment")
    private List<CommentEntity> comment;
    @JsonIgnore
  @OneToMany(mappedBy = "id_vote")
    private List<RatingBookEntity> vote;
}
