package com.example.swp391_fall24_be.config;

import com.example.swp391_fall24_be.core.ResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<String> handleValidationExceptions(MethodArgumentNotValidException exception){
        List<String> errorList = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errorList.add(error.getDefaultMessage());
        });

        return new ResponseDto<>(
                HttpStatus.BAD_REQUEST.value(),
                "Violate validation constraint",
                null,
                errorList
        );
    }
}
