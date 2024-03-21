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
@Table(name="Library")
public class LibraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lib;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_user")
    private UsersEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_book")
    private BooksEntity book;
}
