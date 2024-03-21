package ru.readles.readlesshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Books {
    private String title;

    private String author;

    private String description;

    private String category;

    private Integer price;

    private String urlImg;
}
