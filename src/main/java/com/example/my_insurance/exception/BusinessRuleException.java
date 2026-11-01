package com.example.my_insurance.exception;

public class BusinessRuleException extends RuntimeException {
    //constructor
    public BusinessRuleException(String message) {
        super(message);
    }
}
