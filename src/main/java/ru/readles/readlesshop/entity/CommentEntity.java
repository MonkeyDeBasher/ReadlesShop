package ru.readles.readlesshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_user")
    private UsersEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_book")
    private BooksEntity book;

    private String description;

}
