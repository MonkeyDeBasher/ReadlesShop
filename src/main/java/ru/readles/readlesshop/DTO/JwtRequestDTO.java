package ru.readles.readlesshop.DTO;

import lombok.Data;

@Data
public class JwtRequestDTO {
    private String login;
    private String password;
}
