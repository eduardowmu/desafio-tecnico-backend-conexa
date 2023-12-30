package br.conexa.agenda.exception;

import jakarta.servlet.ServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentException> throwException(IllegalArgumentException exception,
                                                                   ServletRequest request) {
        return ResponseEntity.ok().body(new IllegalArgumentException(
                System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), exception.getLocalizedMessage()));
    }
}