package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.entity.LibraryEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping
    public ResponseEntity createLib(@RequestParam Long userId, @RequestParam Long bookId){
        try {
            libraryService.createLib(userId,bookId);
            return ResponseEntity.ok("Книга успешно добавлена  библеотеку");
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
