package com.example.my_insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.my_insurance.entity.InsurancePolicy;
import com.example.my_insurance.entity.InsurancePolicy.PolicyStatus;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
    /*findById(), findAll(), save(), deleteById() is inherited by JpaRepository*/
    List<InsurancePolicy> findByPolicyNumber(String policyNumber);
    List<InsurancePolicy> findByStatus(PolicyStatus status);
    
    List<InsurancePolicy> findByClient_clientId(Long clientId);
    List<InsurancePolicy> findByVehicle_vehicleId(Long VehicleId);
}
