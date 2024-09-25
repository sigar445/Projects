package org.sigarLLD.ConcurrentURLShortener.Exceptions;

import org.sigarLLD.ConcurrentURLShortener.Helper.InvalidUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<String> handleInvalidUrlException(InvalidUrlException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
//
//    @ExceptionHandler(UrlNotFoundException.class)
//    public ResponseEntity<String> handleUrlNotFoundException(UrlNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}

