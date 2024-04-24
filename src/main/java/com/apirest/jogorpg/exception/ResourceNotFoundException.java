package com.apirest.jogorpg.exception;

public class ResourceNotFoundException extends InvalidInputException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
