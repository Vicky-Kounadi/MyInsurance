package com.example.my_insurance.dto.response;

public class ClientResponse {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;

    //constuctor
    public ClientResponse(Long clientId, String firstName, String lastName, String email){
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //jpa constuctor
    public ClientResponse() {
    }
    
    //setters
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    //getters
    public Long getClientId() {
        return clientId;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getEmail() {
        return email;
    }
}