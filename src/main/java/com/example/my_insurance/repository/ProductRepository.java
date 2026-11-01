package com.example.my_insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.my_insurance.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /*findById(), findAll(), save(), deleteById() is inherited by JpaRepository*/ 
    List<Product> findByProductCode(String productCode);
    List<Product> findByProductName(String productName);
    List<Product> findByActive(boolean active);
}