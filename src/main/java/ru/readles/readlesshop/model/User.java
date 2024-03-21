package ru.readles.readlesshop.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.readles.readlesshop.entity.UsersEntity;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id_user;
    private String login;
    private String email;
    private String role;

    public static User toModel(UsersEntity entity){
        User model = new User();
        model.setId_user(entity.getId_user());
        model.setLogin(entity.getLogin());
        model.setEmail(entity.getEmail());
        model.setRole(entity.getRole());
        return model;
    }

}
