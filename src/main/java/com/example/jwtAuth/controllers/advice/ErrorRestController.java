package com.example.jwtAuth.controllers.advice;

import com.example.jwtAuth.exceptions.ServiceException;
import com.example.jwtAuth.shared.utils.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ErrorRestController {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> resourceNotFoundException(ServiceException e) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
    }
}
