package ru.readles.readlesshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readles.readlesshop.service.RatingService;
import ru.readles.readlesshop.service.UsersService;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @PostMapping
    public ResponseEntity addRating(@RequestParam Long bookId, @RequestHeader(required = false, value = "Authorization")  String token){
        try{
            return ResponseEntity.ok(ratingService.addRating(bookId, token));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
  @GetMapping
    public  ResponseEntity getRating(@RequestParam Long bookId){
try {
    return ResponseEntity.ok(ratingService.getRatingBook(bookId));
} catch (Exception e){
    return ResponseEntity.badRequest().body(e.getMessage());
}
    }
}
