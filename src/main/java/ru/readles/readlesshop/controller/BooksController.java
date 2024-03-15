package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity addBooks(@RequestBody BooksEntity booksEntity){
        try {
            booksService.addBooks(booksEntity);
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
            return ResponseEntity.badRequest().body("Пользователь не найден!");
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        try {
            return ResponseEntity.ok(booksService.deleteBook(id));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Удалить запись не удалось!");
        }
    }
    @PutMapping("{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody BooksEntity booksEntityUpdate){
        try {
            return ResponseEntity.ok(booksService.updateBooks(booksEntityUpdate));
        }
        catch (BookNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
