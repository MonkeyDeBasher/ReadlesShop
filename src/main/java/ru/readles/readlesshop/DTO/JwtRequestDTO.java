package ru.readles.readlesshop.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtRequestDTO {
    private String login;
    private String password;
}
