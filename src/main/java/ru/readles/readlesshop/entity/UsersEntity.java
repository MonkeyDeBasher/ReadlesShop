package ru.readles.readlesshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="Users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String login;

    private String email;

    private String password;

    private String status;

    private String description;

    private String role = "USER";
    @OneToMany(mappedBy = "id_lib", fetch = FetchType.LAZY)
    private List<LibraryEntity> libraries;
    @OneToMany(mappedBy = "id_comment", fetch = FetchType.LAZY)
    private List<CommentEntity> comment;
    @OneToMany(mappedBy = "id_vote", fetch = FetchType.LAZY)
    private List<RatingBookEntity> vote;
}
