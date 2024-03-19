package ru.readles.readlesshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String login;

    private String email;

    private String password;

    private String role = "USER";
    @OneToMany(mappedBy = "user")
    private List<LibraryEntity> libraries;
    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comment;

    public UsersEntity() {
    }
    public String getLogin() {
        return login;
    }

    public Long getId_user() {
        return id_user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
