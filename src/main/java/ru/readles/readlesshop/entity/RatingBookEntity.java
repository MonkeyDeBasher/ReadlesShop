package ru.readles.readlesshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Rating_book")
public class RatingBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vote;

    private Long vote;

 @ManyToOne
 @JoinColumn(name="id_user")
  private UsersEntity user;
   @ManyToOne
  @JoinColumn(name="id_book")
   private BooksEntity book;
}

