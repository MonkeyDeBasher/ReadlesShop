package ru.readles.readlesshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.DTO.UserRegisterDTO;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.UserAlreadyException;
import ru.readles.readlesshop.exception.UserNotFoundException;
import ru.readles.readlesshop.repository.UsersRepository;
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

//    @GetMapping
//    public ResponseEntity getOneUser(@RequestParam Long id) {
//        try {
//            return ResponseEntity.ok(usersService.getOneUser(id));
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Пользователь не найден!");
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usersService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Сервер не смог запуститься!");
        }
    }
}
