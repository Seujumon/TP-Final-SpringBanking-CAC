package com.SpringBanking.api.exceptions.handlers;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.SpringBanking.api.exceptions.AccountNotExistsException;
import com.SpringBanking.api.exceptions.TransferNotExistsException;
import com.SpringBanking.api.exceptions.UserNotExistsException;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler({ UserNotExistsException.class, TransferNotExistsException.class,
            AccountNotExistsException.class })
    public ResponseEntity<?> handlerClassNotExist(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found.\n" + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlerHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body. \n" + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handlerPathTypeMissmatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    
}
