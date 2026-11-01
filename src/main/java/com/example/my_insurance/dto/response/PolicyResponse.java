package com.example.my_insurance.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.my_insurance.entity.InsurancePolicy.PolicyStatus;

public class PolicyResponse {
    //policy only
    private Long policyId;
    private String policyNumber;

    private String description;
    private PolicyStatus status;
    private BigDecimal monthlyPremium;
    private LocalDate startDate;
    private LocalDate endDate;
    
    //get their data
    private ClientResponse cr;
    private VehicleResponse vr;
    private ProductResponse pr;

    //constructor
    public PolicyResponse(Long policyId, String policyNumber, String description, PolicyStatus status, 
                        BigDecimal monthlyPremium,LocalDate startDate, LocalDate endDate,
                        ClientResponse cr, VehicleResponse vr, ProductResponse pr) {

        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.description = description;
        this.status = status;
        this.monthlyPremium = monthlyPremium;
        this.startDate = startDate;
        this.endDate = endDate;
        
        this.cr = cr;
        this.vr = vr;
        this.pr = pr;
    }
    
    //jpa constructor
    public PolicyResponse() {
    }

    //setters
    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public void setClient(ClientResponse cr) {
        this.cr = cr;
    }

    public void setVehicle(VehicleResponse vr) {
        this.vr = vr;
    }

    public void setProduct(ProductResponse pr) {
        this.pr = pr;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public void setMonthlyPremium(BigDecimal monthlyPremium) {
        this.monthlyPremium = monthlyPremium;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    //getters
    public Long getPolicyId() {
        return policyId;
    }
    
    public String getPolicyNumber() {
        return policyNumber;
    }
    
    public String getDescription() {
        return description;
    }
    
    public PolicyStatus getStatus() {
        return status;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public BigDecimal getMonthlyPremium() {
        return monthlyPremium;
    }
    
    //steal from other response odts
    public ClientResponse getClient() {
        return cr;
    }

    public VehicleResponse getVehicle() {
        return vr;
    }

    public ProductResponse getProduct() {
        return pr;
    }
}