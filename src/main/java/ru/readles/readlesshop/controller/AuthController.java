package ru.readles.readlesshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.readles.readlesshop.DTO.JwtRequest;
import ru.readles.readlesshop.DTO.JwtResponse;
import ru.readles.readlesshop.config.MyUserDetails;
import ru.readles.readlesshop.config.MyUserDetailsService;
import ru.readles.readlesshop.exception.AppErrorException;
import ru.readles.readlesshop.service.UsersService;
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

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            //Закинуть в сервис
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        }
        catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppErrorException(HttpStatus.UNAUTHORIZED.value(),"неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = usersService.loadUserByUsername(authRequest.getLogin());
        String token = jwtUtils.generation(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }
}
