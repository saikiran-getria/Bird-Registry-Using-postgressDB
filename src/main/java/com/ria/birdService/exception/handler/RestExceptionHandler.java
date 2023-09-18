package com.ria.birdService.exception.handler;


import com.ria.birdService.exception.BirdAppException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BirdAppException.class)
    public ResponseEntity<Object> handleBirdAppException(BirdAppException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(Instant.now(), ex.getMessage()), ex.getStatus());
    }

}

