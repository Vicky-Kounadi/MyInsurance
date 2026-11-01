package com.example.my_insurance.exception;

public class NotFoundException extends RuntimeException {
    //constructor
    public NotFoundException(String message) {
        super(message);
    }
}