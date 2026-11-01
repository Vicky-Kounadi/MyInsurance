package com.example.my_insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.my_insurance.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    /*findById(), findAll(), save(), deleteById() is inherited by JpaRepository*/
    List<Client> findByEmail(String email);
    List<Client> findByLastName(String lastName);
}
