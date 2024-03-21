package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.DTO.CommentDTO;
import ru.readles.readlesshop.entity.BooksEntity;
import ru.readles.readlesshop.entity.UsersEntity;
import ru.readles.readlesshop.exception.BookNotFoundException;
import ru.readles.readlesshop.service.BooksService;
import ru.readles.readlesshop.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping
    public ResponseEntity createCom(@RequestBody CommentDTO commentDTO, @RequestParam Long userId, @RequestParam Long bookId){
        try {
            return ResponseEntity.ok(commentService.createCom(commentDTO, userId, bookId));
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
