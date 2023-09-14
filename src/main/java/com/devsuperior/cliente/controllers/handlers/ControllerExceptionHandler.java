package com.devsuperior.cliente.controllers.handlers;


import com.devsuperior.cliente.dto.CustomErrorDto;
import com.devsuperior.cliente.dto.ValidationErrorDto;
import com.devsuperior.cliente.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDto> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDto error = new CustomErrorDto(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDto> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorDto error = new ValidationErrorDto(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
        e.getBindingResult().getFieldErrors().forEach(f -> error.addError(f.getField(), f.getDefaultMessage()));
        return ResponseEntity.status(status).body(error);
    }
}
