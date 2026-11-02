package com.example.my_insurance.controller;

import com.example.my_insurance.dto.request.CreatePolicyRequest;
import com.example.my_insurance.dto.request.UpdatePolicyStatusRequest;
import com.example.my_insurance.dto.response.PolicyResponse;
import com.example.my_insurance.entity.InsurancePolicy.PolicyStatus;
import com.example.my_insurance.service.PolicyService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    
    //CREATE POLICY
    @PostMapping("/policies")
    public ResponseEntity<PolicyResponse> createPolicy(@Valid @RequestBody 
                                                    CreatePolicyRequest request){
        PolicyResponse ps= policyService.createPolicy(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ps);
    }
    
    //GET POLICY BY NUMBER
    @GetMapping("/policies/{policyNumber}") //I need parameter- add in URL
    public ResponseEntity<PolicyResponse> getPolicyByNumber(
                                                @PathVariable String policyNumber){

        PolicyResponse ps = policyService.getPolicyByNumber(policyNumber);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ps);
    }
    
    //GET ALL POLICIES
    @GetMapping("/policies")
    public ResponseEntity<List<PolicyResponse>> getAllPolicies(){

        List<PolicyResponse> ps = policyService.getAllPolicies();

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ps);
    }
    
    //GET POLICIES BY CLIENT
    @GetMapping("/clients/{clientId}/policies") //I need parameter- add in URL
    public ResponseEntity<List<PolicyResponse>> getPoliciesByClient(
                                                    @PathVariable Long clientId) {

        List<PolicyResponse> ps = policyService.getPoliciesByClient(clientId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ps);
    }
    
    //GET POLICY STATUS
    @GetMapping("/policies/{policyNumber}/status")
    public ResponseEntity<PolicyStatus> getPolicyStatus(
                                            @PathVariable String policyNumber) {

        PolicyStatus ps = policyService.getPolicyStatus(policyNumber);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ps);
    }
    
    //UPDATE POLICY STATUS
    @PatchMapping("/policies/{policyNumber}/status")
    public ResponseEntity<PolicyResponse> updatePolicyStatus(
                                @PathVariable String policyNumber,
                                @Valid @RequestBody UpdatePolicyStatusRequest request){

        PolicyResponse ps = policyService.updatePolicyStatus(policyNumber, request);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ps);
    }
    
    //DELETE POLICY
    @DeleteMapping("/policies/{policyNumber}")
    public ResponseEntity<String> deletePolicy(
                            @PathVariable String policyNumber){

        policyService.deletePolicy(policyNumber);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Policy deleted successfully");
    }
}
