package ru.readles.readlesshop.exception;

import lombok.Data;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Date;


@Data
public class AppErrorException extends RuntimeException {
    private int status;
    private String message;
    private Date timestamp;

    public AppErrorException(int value, String message, Date date) {
        this.status = value;
        this.message = message;
        this.timestamp=date;
    }
}
