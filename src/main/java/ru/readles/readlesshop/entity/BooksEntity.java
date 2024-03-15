package ru.readles.readlesshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
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
    @OneToMany(mappedBy = "book")
    private List<LibraryEntity> libraries;


    public BooksEntity(){

    }
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
    public String getUrlImg() {
        return urlImg;
    }
    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
