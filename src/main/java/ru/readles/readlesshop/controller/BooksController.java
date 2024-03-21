package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.DTO.BookDTO;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.exception.UserNotFoundException;
import ru.readles.readlesshop.service.BooksService;

@RestController
@RequestMapping("/book")
public class BooksController {
    @Autowired
    private BooksService booksService;
    @PostMapping
    public ResponseEntity addBooks(@RequestBody BookDTO bookDTO){
        try {
            booksService.addBooks(bookDTO);
            return ResponseEntity.ok("Книга успешно добавлена!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления");
        }
    }
    @GetMapping
    public ResponseEntity getBook(@RequestParam Long id){
        try {
            return ResponseEntity.ok(booksService.getBook(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Книга не найдена!");
        }
    }
    @PutMapping
    public ResponseEntity updateBook(@RequestParam Long id, @RequestBody BookDTO bookDTO){
        try {
            return ResponseEntity.ok(booksService.putBooks(bookDTO, id));
        }
        catch (BookNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
