package ru.readles.readlesshop.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
