package com.example.my_insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.my_insurance.dto.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //NotFoundException
    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleClientNotFound(SearchNotFoundException ex) {
        //the http result type

        ApiErrorResponse error = new ApiErrorResponse(
            404,
            "Not Found",
            ex.getMessage()
        );

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(error);
    }

    //BusinessRuleException
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiErrorResponse> handleProductNotFound(BusinessRuleException ex) {

        ApiErrorResponse error = new ApiErrorResponse(
            400,
            "Bad Request",
            ex.getMessage()
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(error);
    }

    //DuplicateException
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidPolicy(DuplicateException ex) {

        ApiErrorResponse error = new ApiErrorResponse(
            409,
            "Conflict",
            ex.getMessage()
        );

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(error);
    }
    
}


