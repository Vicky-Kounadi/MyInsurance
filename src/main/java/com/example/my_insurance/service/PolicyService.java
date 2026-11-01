package com.example.my_insurance.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.my_insurance.dto.response.ClientResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.my_insurance.dto.request.CreatePolicyRequest;
import com.example.my_insurance.dto.response.PolicyResponse;
import com.example.my_insurance.dto.response.ProductResponse;
import com.example.my_insurance.dto.response.VehicleResponse;
import com.example.my_insurance.entity.Client;
import com.example.my_insurance.entity.InsurancePolicy;
import com.example.my_insurance.entity.InsurancePolicy.PolicyStatus;
import com.example.my_insurance.entity.Product;
import com.example.my_insurance.entity.Vehicle;
import com.example.my_insurance.repository.ClientRepository;
import com.example.my_insurance.repository.InsurancePolicyRepository;
import com.example.my_insurance.repository.ProductRepository;
import com.example.my_insurance.repository.VehicleRepository;

import jakarta.validation.Valid;

@Service
public class PolicyService {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final VehicleRepository vehicleRepository;
    private final InsurancePolicyRepository policyRepository;
    
    //constructor
    public PolicyService(ClientRepository clientRepository, ProductRepository productRepository,
                        VehicleRepository vehicleRepository,InsurancePolicyRepository policyRepository){
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.vehicleRepository = vehicleRepository;
        this.policyRepository = policyRepository;
    }
    
    public PolicyResponse makePolicyResponse(InsurancePolicy policy){
        //make the response
        ClientResponse cResponse= new ClientResponse(
            policy.getClient().getClientId(),
            policy.getClient().getFirstName(),
            policy.getClient().getLastName(),
            policy.getClient().getEmail()
        );
        
        VehicleResponse vResponse= new VehicleResponse(
            policy.getVehicle().getVehicleId(),
            policy.getVehicle().getRegistrationNumber(),
            policy.getVehicle().getManufacturer(),
            policy.getVehicle().getModel()
        );
        
        ProductResponse pResponse= new ProductResponse(
            policy.getProduct().getProductId(),
            policy.getProduct().getProductCode(),
            policy.getProduct().getProductName()
        );
        
        return new PolicyResponse(
            policy.getPolicyId(),
            policy.getPolicyNumber(),
            policy.getDescription(),
            policy.getStatus(),
            policy.getMonthlyPremium(),
            policy.getStartDate(),
            policy.getEndDate(),
            cResponse,
            vResponse,
            pResponse
        );
    }
    
    //CREATE A NEW POLICY
    @Transactional
    public PolicyResponse createPolicy(CreatePolicyRequest request){
        //validate request correctness first and then check the DB
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getMonthlyPremium() == null || request.getMonthlyPremium().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Premium must be greater than 0");
        }
        if (request.getStartDate() == null || request.getEndDate() == null ||
            request.getEndDate().compareTo(request.getStartDate()) <= 0) {
            throw new IllegalArgumentException("Invalid dates");
        }

        //find client, vehicle, product in theor reps
        Client c = clientRepository.findById(request.getClientId()).orElse(null);
        Product p = productRepository.findById(request.getProductId()).orElse(null);
        Vehicle v = vehicleRepository.findById(request.getVehicleId()).orElse(null);
    
        //if client n product requested DONT exist in the DB throw exception
        if(c == null){
            throw new IllegalArgumentException("Client "+ request.getClientId() + " does not exist"); //404
        }
        if(p == null){
            throw new IllegalArgumentException("Product "+ request.getProductId() + " does not exist"); //404
        }
        if(v == null){
            throw new IllegalArgumentException("Vehicle "+ request.getVehicleId() + " does not exist"); //404
        }
        
        //if product requested IS NOT active throw exception
        if(!p.isActive()){
            throw new IllegalArgumentException("Product "+ request.getProductId() + " is not active"); //400
        }
        if(v.getClient().getClientId() != c.getClientId()){
            throw new IllegalArgumentException("Client "+ request.getClientId() + " does not own Vehicle "+ request.getVehicleId()); //400
        }
        
        //make the new policy with the request fields
        InsurancePolicy policy= new InsurancePolicy(generatePolicyNumber(), 
            request.getDescription(), PolicyStatus.PENDING, request.getMonthlyPremium(),
                request.getStartDate(),request.getEndDate(),
                    c,p,v);
 
        //save the new policy in the DB and store it
        InsurancePolicy saved = policyRepository.save(policy);

        return makePolicyResponse(saved);
           
    }
    
    //NOTE-TO-SELF: I changed lenght from 20 to 40, cause uuid produces longer than expected
    //https://www.w3schools.com/tools/tool_uuid.php
    private String generatePolicyNumber(){
        return "IP-"+ java.util.UUID.randomUUID();
    }
    
    //GET POLICY BY NUMBER
    public PolicyResponse getPolicyByNumber(String policyNumber) {
        List<InsurancePolicy> ps = policyRepository.findByPolicyNumber(policyNumber);
        //no policies
        if (ps.isEmpty()) {
            throw new IllegalArgumentException("Policy " +policyNumber+ " not found");//404
        }
        
        return makePolicyResponse(ps.get(0));
    }
    
    //GET ALL POLICIES
    public List<PolicyResponse> getAllPolicies() {
        List<InsurancePolicy> policies = policyRepository.findAll();
        //no policies
        if (policies.isEmpty()) {
            throw new IllegalArgumentException("No policies found");//404
        }
        
        List<PolicyResponse> allPolicies = new ArrayList<>();

        for (InsurancePolicy ps : policies) {
            PolicyResponse pResponse = makePolicyResponse(ps);
            allPolicies.add(pResponse);
        }
        return allPolicies;
    }

    //GET POLICIES BY CLIENT
    public List<PolicyResponse> getPoliciesByClient(Long clientId) {
        //check if client exists
        if (!clientRepository.existsById(clientId)) {
            throw new IllegalArgumentException("Client " +clientId +" not found");//404
        }
    
        List<InsurancePolicy> policies = policyRepository.findByClient_clientId(clientId);
        //no policies
        if (policies.isEmpty()) {
            throw new IllegalArgumentException("Client has no policies");//404
        }
        
        List<PolicyResponse> allPolicies = new ArrayList<>();

        for (InsurancePolicy ps : policies) {
            PolicyResponse pResponse = makePolicyResponse(ps);
            allPolicies.add(pResponse);
        }
        return allPolicies;
    }
    
    // GET POLICY STATUS
    public PolicyStatus getPolicyStatus(String policyNumber){
        List<InsurancePolicy> policies = policyRepository.findByPolicyNumber(policyNumber);
        //no policies
        if (policies.isEmpty()) {
            throw new IllegalArgumentException("Policy " +policyNumber+ " not found");//404
        }

        return policies.get(0).getStatus();
    }
    
    // DELETE POLICY BY NUMBER
    @Transactional 
    public void deletePolicy(String policyNumber){
        List<InsurancePolicy> policies = policyRepository.findByPolicyNumber(policyNumber);
        //no policies
        if (policies.isEmpty()) {
            throw new IllegalArgumentException("Policy " +policyNumber+ " not found");//404
        }

        policyRepository.delete(policies.get(0));
    }
}
