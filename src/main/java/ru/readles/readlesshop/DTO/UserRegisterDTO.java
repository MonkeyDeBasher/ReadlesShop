package ru.readles.readlesshop.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {
    @NotNull
    private String login;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
