package com.example.my_insurance.service;

import java.math.BigDecimal;

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
        ClientResponse c_response= new ClientResponse(
            policy.getClient().getClientId(),
            policy.getClient().getFirstName(),
            policy.getClient().getLastName(),
            policy.getClient().getEmail()
        );
        
        VehicleResponse v_response= new VehicleResponse(
            policy.getVehicle().getVehicleId(),
            policy.getVehicle().getRegistrationNumber(),
            policy.getVehicle().getManufacturer(),
            policy.getVehicle().getModel()
        );
        
        ProductResponse p_response= new ProductResponse(
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
            c_response,
            v_response,
            p_response
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
        InsurancePolicy p = policyRepository.findByPolicyNumber(policyNumber).orElse(null);
        if (p == null) {
            throw new IllegalArgumentException("Policy " +policyNumber+ " not found");//404
        }
        
        return makePolicyResponse(p);
    }

}
