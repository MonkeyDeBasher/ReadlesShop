package ru.readles.readlesshop.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comment;

    @ManyToOne
    @JoinColumn(name="id_user")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name="id_book")
    private BooksEntity book;

    private String description;

    public Long getId_comment() {
        return id_comment;
    }

    public void setId_comment(Long id_comment) {
        this.id_comment = id_comment;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public BooksEntity getBook() {
        return book;
    }

    public void setBook(BooksEntity book) {
        this.book = book;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
