package com.example.my_insurance.dto.response;

public class ApiErrorResponse {
    private int status;
    private String error;
    private String message;

    //constructor
    public ApiErrorResponse(int status, String error, String message){
        this.status = status;
        this.error = error;
        this.message = message;
    }

    //getters
    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
