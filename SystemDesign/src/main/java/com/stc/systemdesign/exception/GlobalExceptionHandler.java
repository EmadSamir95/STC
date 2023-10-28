package com.stc.systemdesign.exception;

import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.stc.systemdesign")
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex){
        logger.error("validation error caught with message " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PermissionGroupNotFoundException.class)
    public ResponseEntity<?> handleValidationException(PermissionGroupNotFoundException ex){
        logger.error("permission exception caught with message " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex){
        logger.error("internal error occurred " + ex.getMessage());
        return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        logger.error("internal error occurred " + ex.getMessage());
        return new ResponseEntity<>("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
