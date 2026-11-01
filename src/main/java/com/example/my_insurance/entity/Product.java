package main.java.com.example.my_insurance.entity;

import java.math.BigDecimal;

public class Product {
    private final long productId;
    private String productCode;
    private String productName;
    private String description;
    private BigDecimal basePremium;
    private boolean active;

    //constructor
    public Product(long productId, String productCode, String productName, String description, BigDecimal basePremium){
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.basePremium = basePremium;
        this.active = true;
    }

    //setters
    public void setProductCode(String productCode){
        this.productCode = productCode; 
    }
    
    public void setProductName(String productName){
        this.productName = productName; 
    }
    
    public void setDescription(String description){
        this.description = description; 
    }
    
    public void setBasePremium(BigDecimal basePremium){
        if(basePremium ==null || basePremium.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Premium cannot be negative");
        }
        this.basePremium = basePremium; 
    }
    
    public void setActive(boolean active){
        this.active = active; 
    }
    
    //getters
    public long getProductId(){
        return productId; 
    }
    
    public String getProductCode(){
        return productCode; 
    }
    
    public String getProductName(){
        return productName; 
    }
    
    public String getDescription(){
        return description; 
    }
    
    public BigDecimal getBasePremium(){
        return basePremium; 
    }
    
    public boolean isActive(){
        return active; 
    }
    
    //object tostring
    @Override
    public String toString() {
        return "Product => {" +
                "id='" + productId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", basePremium='" + basePremium + '\'' +
                ", active='" + active + '\'' +  
                ", description='" + description + '\'' +                   
                "}"
                ;
    }
    
    //equals and hashcode
    
    //methods

}
