package com.example.my_insurance.entity;

import jakarta.persistence.*;

@Entity
@Table(name="client")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private Long clientId;
    
    @Column(name="last_name", nullable=false, length = 50)
    private String lastName;
    
    @Column(name="first_name", nullable=false, length = 50)
    private String firstName;
    
    @Column(name="email", nullable=false, unique=true, length = 100)
    private String email;
    
    @Column(name="phone", nullable=false, length = 20)
    private String phone;

    //constructor
    public Client(String lastName, String firstName, String email, String phone){
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
    }
    
    //JPA constructor
    protected Client() {
    }

    //setters
    public void setLastName(String lastName){
        this.lastName = lastName; 
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
        
    //getters
    public Long getClientId(){
        return clientId; 
    }
    
    public String getLastName(){
        return lastName; 
    }
    
    public String getFirstName(){
        return firstName; 
    }
    
    public String getEmail(){
        return email; 
    }
    
    public String getPhone(){
        return phone; 
    }
    
    //object tostring
    @Override
    public String toString() {
        return "Client => {" +
                "id='" + clientId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +                    
                "}"
                ;
    }
    
    //equals and hashcode
    
    //methods
    //must add a phone number and email checker function and use it in the setters
}

