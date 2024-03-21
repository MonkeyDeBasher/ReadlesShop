package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.DTO.UserRegisterDTO;
import ru.readles.readlesshop.exception.RightsMismatchUsersException;
import ru.readles.readlesshop.exception.UserAlreadyException;
import ru.readles.readlesshop.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @PostMapping
    public ResponseEntity registration(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            usersService.registration(userRegisterDTO);
            return ResponseEntity.ok("Пользователь успешно сохранён!");
        } catch (UserAlreadyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getUser(@RequestParam String login,  @RequestHeader(required = false, value = "Authorization")  String token) {
        try{
            return ResponseEntity.ok(usersService.getUser(login, token));
        }
        catch (RightsMismatchUsersException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
