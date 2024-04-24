package com.apirest.jogorpg.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("Invalid Payload");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
