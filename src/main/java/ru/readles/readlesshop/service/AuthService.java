package ru.readles.readlesshop.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.readles.readlesshop.DTO.JwtRequestDTO;
import ru.readles.readlesshop.config.MyUserDetailsService;
import ru.readles.readlesshop.exception.AppErrorException;
import ru.readles.readlesshop.utils.JwtUtils;

import java.util.Date;

@Service
public class AuthService {

    private final MyUserDetailsService usersService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthService(MyUserDetailsService usersService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.usersService = usersService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }
    public String auth(JwtRequestDTO authRequest) throws AppErrorException {
        Date date = new java.util.Date();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new AppErrorException(HttpStatus.UNAUTHORIZED.value(),"неправильный логин или пароль",date);
        }
        UserDetails userDetails = usersService.loadUserByUsername(authRequest.getLogin());
        String token = jwtUtils.generation(userDetails);
        return token;
    }
}
