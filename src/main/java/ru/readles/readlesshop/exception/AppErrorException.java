package ru.readles.readlesshop.exception;

import lombok.Data;

import java.util.Date;

@Data
public class AppErrorException {
    private int status;
    private String message;
    private Date timestamp;

    public AppErrorException(int status, String message, Date timestamp){
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public AppErrorException(int value, String message) {
    }
}
