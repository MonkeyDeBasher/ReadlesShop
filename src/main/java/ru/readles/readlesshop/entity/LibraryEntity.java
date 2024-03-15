package ru.readles.readlesshop.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Library")
public class LibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lib;

    @ManyToOne
    @JoinColumn(name="id_user")
    private UsersEntity user;
    @ManyToOne
    @JoinColumn(name="id_book")
    private BooksEntity book;

    public LibraryEntity(){

    }

    public Long getId_lib() {
        return id_lib;
    }

    public void setId_lib(Long id_lib) {
        this.id_lib = id_lib;
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
}
