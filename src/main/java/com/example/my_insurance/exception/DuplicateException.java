package com.example.my_insurance.exception;

public class DuplicateException extends RuntimeException {
    //constructor
    public DuplicateException(String message) {
        super(message);
    }
}
