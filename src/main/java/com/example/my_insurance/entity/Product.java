package main.java.com.example.my_insurance.entity;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name="product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;
    
    @Column(name="product_code", nullable=false, unique=true, length = 10)
    private String productCode;
    
    @Column(name="product_name", nullable=false, length = 50)
    private String productName;
    
    @Column(name="description", length = 100)
    private String description;
    
    @Column(name="base_premium", nullable=false, precision = 10, scale = 2)
    private BigDecimal basePremium;
    
    @Column(name="active", nullable=false)
    private boolean active;

    //constructor
    public Product(String productCode, String productName, String description, BigDecimal basePremium){
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.basePremium = basePremium;
        //default value is true
        this.active = true;
    }
    
    //JPA constructor
    protected Product(){
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
    public Long getProductId(){
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
