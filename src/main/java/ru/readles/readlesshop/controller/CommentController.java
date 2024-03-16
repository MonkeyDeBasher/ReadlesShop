package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.entity.CommentEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.service.BooksService;
import ru.readles.readlesshop.service.CommentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BooksService booksService;
    @PostMapping
    public ResponseEntity createCom(@RequestBody CommentEntity commentEntity, @RequestParam Long userId, @RequestParam Long bookId){
        try {
            return ResponseEntity.ok(commentService.createCom(commentEntity, userId, bookId));
        }
        catch(BookNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity getCommentsByBookId(@RequestParam Long bookId) throws BookNotFoundException {
        try {
            return ResponseEntity.ok(commentService.getCom(bookId));
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
