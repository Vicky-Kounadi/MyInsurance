package com.example.my_insurance.dto.response;

public class ProductResponse {
    private Long productId;
    private String productCode;
    private String productName;

    //constructor
    public ProductResponse(Long productId, String productCode, String productName){
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
    }
    
    //jpa constructor
    public ProductResponse() {
    }

    //setters
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    //getters
    public Long getProductId() {
        return productId;
    }
    
    public String getProductCode() {
        return productCode;
    }
    
    public String getProductName() {
        return productName;
    }
}