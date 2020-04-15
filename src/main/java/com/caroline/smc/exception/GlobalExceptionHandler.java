package com.caroline.smc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.security.SignatureException;


@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = NotExistException.class)
    public ResponseEntity<ErrorMessage> handleNotExistException(NotExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
	@ExceptionHandler(value = AlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handleUserEmailAlreadyExistException(AlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
    @ExceptionHandler(value = SignatureException.class)
    public ResponseEntity<ErrorMessage> handleSignatureException(SignatureException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }
}
