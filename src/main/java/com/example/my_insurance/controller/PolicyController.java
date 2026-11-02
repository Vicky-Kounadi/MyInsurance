package com.example.my_insurance.controller;

import com.example.my_insurance.dto.request.CreatePolicyRequest;
import com.example.my_insurance.dto.response.PolicyResponse;
import com.example.my_insurance.service.PolicyService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    // GET POLICY BY NUMBER
    @GetMapping("/policies/{policyNumber}") //I need parameter- add in URL
    public ResponseEntity<PolicyResponse> getPolicyByNumber(
                                                @PathVariable String policyNumber){

        PolicyResponse ps = policyService.getPolicyByNumber(policyNumber);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ps);
    }
    
    // GET ALL POLICIES
    @GetMapping("/policies")
    public ResponseEntity<List<PolicyResponse>> getAllPolicies(){

        List<PolicyResponse> ps = policyService.getAllPolicies();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ps);
    }
    
}
