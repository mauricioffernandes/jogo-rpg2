package com.apirest.jogorpg.config;

import com.apirest.jogorpg.exception.ErrorMassage;
import com.apirest.jogorpg.exception.InvalidInputException;
import com.apirest.jogorpg.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExeptionHandler {

    public ResponseEntity<ErrorMassage> handle(Exception ex, HttpStatus statusCode, String description){
        ErrorMassage errorMassage = new ErrorMassage(statusCode, LocalDateTime.now(), ex.getMessage(), description);
        ex.printStackTrace();
        return new ResponseEntity<>(errorMassage, errorMassage.getStatusCode());
    }
    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<ErrorMassage> invalidInputHandler(Exception ex, WebRequest request){
        return handle(ex, HttpStatus.BAD_REQUEST, request.getDescription(false));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMassage> resourceNotFoundHandler(Exception ex, WebRequest request){
        return handle(ex, HttpStatus.NOT_FOUND, request.getDescription(false));
    }
}
