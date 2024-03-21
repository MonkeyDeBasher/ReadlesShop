package ru.readles.readlesshop.exception;

import org.springframework.security.authentication.BadCredentialsException;

import java.util.Date;


public class AppErrorException extends BadCredentialsException {
    private static final String msg = null;
    private static final Throwable cause = null;
    private int status;
    private String message;
    private Date timestamp;

    public AppErrorException(int status, String message, Date timestamp) {
        super(msg, cause);
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

}
