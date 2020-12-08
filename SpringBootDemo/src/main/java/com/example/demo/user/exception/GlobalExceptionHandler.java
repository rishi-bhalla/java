package com.example.demo.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        logger.info("Inside GlobalExceptionHandler -> resourceNotFoundException()");
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        logger.info("Inside GlobalExceptionHandler -> globalExceptionHandler()");
        ErrorResponse errorResponse = new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
