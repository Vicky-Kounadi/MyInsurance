package com.example.my_insurance.controller;

import com.example.my_insurance.dto.request.CreatePolicyRequest;
import com.example.my_insurance.dto.response.PolicyResponse;
import com.example.my_insurance.service.PolicyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api")
public class PolicyController {
    private PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService=policyService;
    }
    
    // CREATE POLICY
    @PostMapping("/policies")
    public ResponseEntity<PolicyResponse> createPolicy(@Valid @RequestBody 
                                                    CreatePolicyRequest request){
        PolicyResponse ps= policyService.createPolicy(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ps);
    }
    
    
}
