package ru.readles.readlesshop.exception;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String message) {
        super(message);
    }
}