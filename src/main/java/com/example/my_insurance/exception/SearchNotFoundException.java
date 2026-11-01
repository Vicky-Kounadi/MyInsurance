package com.example.my_insurance.exception;

public class SearchNotFoundException extends RuntimeException {
    //constructor
    public SearchNotFoundException(String message) {
        super(message);
    }
}