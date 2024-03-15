package ru.readles.readlesshop.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.readles.readlesshop.entity.UsersEntity;

public class User {
    private Long id_user;
    private String login;
    private String email;
    private String role;

    public User(){

    }
    public static User toModel(UsersEntity entity){
        User model = new User();
        model.setId_user(entity.getId_user());
        model.setLogin(entity.getLogin());
        model.setEmail(entity.getEmail());
        model.setRole(entity.getRole());
        return model;
    }
    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
