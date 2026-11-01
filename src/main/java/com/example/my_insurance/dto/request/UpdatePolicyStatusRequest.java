package com.example.my_insurance.dto.request;

import com.example.my_insurance.entity.InsurancePolicy.PolicyStatus;

import jakarta.validation.constraints.NotNull;

public class UpdatePolicyStatusRequest {
    //what i want the frontend request to give me
    @NotNull
    private PolicyStatus status;

    //onstructor
    public UpdatePolicyStatusRequest(PolicyStatus status) {
        this.status = status;
    }
    
    //jpa constructor
    public UpdatePolicyStatusRequest() {
    }
    
    //setters
    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    //getters
    public PolicyStatus getStatus() {
        return status;
    }

}