package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.entity.LibraryEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.service.LibraryService;
import ru.readles.readlesshop.service.UsersService;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping
    public ResponseEntity createLib(LibraryEntity libraryEntity, @RequestParam Long userId, @RequestParam Long bookId){
        try {
            return ResponseEntity.ok(libraryService.createLib(libraryEntity, userId, bookId));
        }
        catch(BookNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity selectLib(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(libraryService.selectBooksByUserId(userId));
        } catch(BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
