package com.example.my_insurance.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="insurance_policy")
public class InsurancePolicy {
    
    public enum PolicyStatus{
    PENDING,
    ACTIVE,
    CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="policy_id")
    private Long policyId;
    
    @Column(name="policy_number", nullable=false, unique=true, length = 40)
    private String policyNumber;
    
    @Column(name="description", length = 100)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false, length = 10)
    private PolicyStatus status;
    
    @Column(name="monthly_premium", nullable=false, precision = 10, scale = 2)
    private BigDecimal monthlyPremium;
    
    @Column(name="start_date", nullable=false)
    private LocalDate startDate;
    
    @Column(name="end_date", nullable=false)
    private LocalDate endDate;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="client_id", nullable=false)
    private Client client;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="vehicle_id", nullable=false)
    private Vehicle vehicle;

    //constructor
    public InsurancePolicy(String policyNumber, String description, PolicyStatus status, 
                           BigDecimal monthlyPremium, LocalDate startDate, LocalDate endDate, 
                           Client client, Product product, Vehicle vehicle){
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

    //JPA constructor
    protected InsurancePolicy(){
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
    public Long getPolicyId(){
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
    //calculate for how Long the cvehicle is insured
}

