package com.example.my_insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.my_insurance.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    /*findById(), findAll(), save(), deleteById() is inherited by JpaRepository*/
    List<Vehicle> findByRegistrationNumber(String registrationNumber);
    List<Vehicle> findByVin(String vin);
    
    List<Vehicle> findByClient_clientId(Long clientId);
}