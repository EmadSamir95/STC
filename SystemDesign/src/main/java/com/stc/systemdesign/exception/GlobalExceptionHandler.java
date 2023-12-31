package com.stc.systemdesign.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice("com.stc.systemdesign")
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex){
        logger.error("validation error caught with message " + ex.getMessage());
        return new ResponseEntity<>(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException ex){
        logger.error("file not found exception is caught " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PermissionGroupNotFoundException.class)
    public ResponseEntity<?> handlePermissionGroupException(PermissionGroupNotFoundException ex){
        logger.error("permission exception caught with message " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SpaceNotFoundException.class)
    public ResponseEntity<?> handleSpaceNotFoundException(SpaceNotFoundException ex){
        logger.error("space not found exception is found " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<?> handleUnAuthorizedException(UnAuthorizedException ex){
        logger.error("un-authorized exception is caught " + ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<?> handleUserNameNotFoundException(UserNameNotFoundException ex){
        logger.error("provided username is not found " + ex.getMessage());
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
