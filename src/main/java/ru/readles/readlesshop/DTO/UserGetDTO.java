package ru.readles.readlesshop.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserGetDTO {
    private String login;
    private String email;
    private String role;
}
