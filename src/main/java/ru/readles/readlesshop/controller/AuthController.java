package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.readles.readlesshop.DTO.JwtRequestDTO;
import ru.readles.readlesshop.DTO.JwtResponseDTO;
import ru.readles.readlesshop.config.MyUserDetailsService;
import ru.readles.readlesshop.exception.AppErrorException;
import ru.readles.readlesshop.service.AuthService;
import ru.readles.readlesshop.utils.JwtUtils;

@RestController
public class AuthController {
    private final MyUserDetailsService usersService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(MyUserDetailsService usersService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.usersService = usersService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }
    @Autowired
    private AuthService authService;
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
            return ResponseEntity.ok(new JwtResponseDTO(authService.auth(authRequest)));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Status: "+ HttpStatus.UNAUTHORIZED.value() + "\nReason: неправильный логин или пароль");
        }
    }
}
