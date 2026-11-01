package main.java.com.example.my_insurance.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import main.java.com.example.my_insurance.entity.Client;
import main.java.com.example.my_insurance.entity.Product;
import main.java.com.example.my_insurance.entity.Vehicle;

public class InsurancePolicy {
    
    public enum PolicyStatus{
    PENDING,
    ACTIVE,
    CANCELLED
    }

    private final long policyId;
    private final String policyNumber;
    private String description;
    private PolicyStatus status;
    private BigDecimal monthlyPremium;
    private LocalDate startDate;
    private LocalDate endDate;
    
    private Client client;
    private Product product;
    private Vehicle vehicle;

    //constructor
    public InsurancePolicy(long policyId, String policyNumber, String description, PolicyStatus status, 
                           BigDecimal monthlyPremium, LocalDate startDate, LocalDate endDate, 
                           Client client, Product product, Vehicle vehicle){
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.description = description;
        this.status = status;
        this.monthlyPremium = monthlyPremium;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.product = product;
        this.vehicle = vehicle;
    }

    //setters
  
    //PolicyNumber is final, immutable, cannot be changed/set in the future
    
    public void setDescription(String description){
        this.description = description; 
    }
    
    public void setStatus(PolicyStatus status){
        this.status = status;
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
        
    public void setClient(Client client){
        this.client = client;
    }
    
    public void setProduct(Product product){
        this.product = product;
    }
        
    public void setVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
    }
        
    //getters
    public long getPolicyId(){
        return policyId; 
    }
    
    public String getPolicyNumber(){
        return policyNumber; 
    }
    
    public String getDescription(){
        return description; 
    }
    
    public PolicyStatus getStatus(){
        return status; 
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
    
    public Client getClient(){
        return client; 
    }
    
    public Product getProduct(){
        return product; 
    }
    
    public Vehicle getVehicle(){
        return vehicle; 
    }
    
    //object tostring
    @Override
    public String toString() {
        return "InsurancePolicy => {" +
                "id='" + policyId + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", status='" + status + '\'' +
                ", monthlyPremium='" + monthlyPremium + '\'' +  
                ", startDate='" + startDate + '\'' + 
                ", endDate='" + endDate + '\'' + 
                ", description='" + description + '\'' + '\n'+
                ", " + client + '\'' + '\n'+     
                ", " + product + '\'' + '\n'+
                ", " + vehicle + '\'' +                 
                "}"
                ;
    }
    
    //equals and hashcode
    
    //methods
    //method to calcuale yearly monthlyPremium
    //calculate for how long the cvehicle is insured
}

