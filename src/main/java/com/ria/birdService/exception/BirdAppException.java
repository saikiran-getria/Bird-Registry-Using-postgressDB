package com.ria.birdService.exception;

import org.springframework.http.HttpStatus;

public class BirdAppException extends RuntimeException {

    public final String message;
    public final HttpStatus status;
    public BirdAppException(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
