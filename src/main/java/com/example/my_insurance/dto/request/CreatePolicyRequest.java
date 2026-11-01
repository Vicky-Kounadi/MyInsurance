package com.example.my_insurance.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class CreatePolicyRequest {
    //what i want the frontend request to give me
    private String description;
    
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal monthlyPremium;
    
    @NotNull
    private LocalDate startDate;
    
    @NotNull
    private LocalDate endDate;
    
    @NotNull
    private Long clientId;
    
    @NotNull
    private Long vehicleId;
    
    @NotNull
    private Long productId;
    
    //constructor
    public CreatePolicyRequest(String description, BigDecimal monthlyPremium, 
                            LocalDate startDate, LocalDate endDate, 
                           Long clientId, Long vehicleId, Long productId){
        this.description = description;
        this.monthlyPremium = monthlyPremium;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientId = clientId;
        this.vehicleId = vehicleId;
        this.productId = productId;
    }

    //jpa constructor
    public CreatePolicyRequest(){ 
    }
    
    //setters
    public void setDescription(String description){
        this.description = description; 
    }
    
    public void setMonthlyPremium(BigDecimal monthlyPremium){
        if(monthlyPremium ==null || monthlyPremium.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Premium cannot be negative");
        }
        this.monthlyPremium = monthlyPremium; 
    }

    public void setStartDate(LocalDate startDate){
        if(startDate==null || startDate.getYear() <1885){
            throw new IllegalArgumentException("Start date cannot be older than year 1885");
        }
        this.startDate = startDate;
    }
    
    public void setEndDate(LocalDate endDate){
        if(startDate==null || endDate==null || endDate.compareTo(startDate) <= 0){
            throw new IllegalArgumentException("End date cannot be after the Start date " + startDate);
        }
        this.endDate = endDate;
    }
    
    //reffered
    public void setClientId(Long clientId){
        this.clientId = clientId; 
    }
    
    public void setProductId(Long productId){
        this.productId = productId; 
    }
    
    public void setVehicleId(Long vehicleId){
        this.vehicleId = vehicleId; 
    }
    
    
    //getters
    public String getDescription(){
        return description; 
    }
    
    public BigDecimal getMonthlyPremium(){
        return monthlyPremium; 
    }
    
    public LocalDate getStartDate(){
        return startDate; 
    }
    
    public LocalDate getEndDate(){
        return endDate; 
    }
    
    //referred
    public Long getClientId(){
        return clientId;  
    }
    
    public Long getProductId(){
        return productId; 
    }
    
    public Long getVehicleId(){
        return vehicleId; 
    }
    

}