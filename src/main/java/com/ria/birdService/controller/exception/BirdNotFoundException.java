package com.ria.birdService.controller.exception;

public class BirdNotFoundException extends RuntimeException{
    public BirdNotFoundException(String id) {
        super(String.format("Bird with Id %s not found", id));
    }
}
