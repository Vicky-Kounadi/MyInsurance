package com.example.my_insurance.dto.response;

public class VehicleResponse {
    private Long vehicleId;
    private String registrationNumber;
    private String manufacturer;
    private String model;

    //constructor
    public VehicleResponse(Long vehicleId, String registrationNumber, String manufacturer, String model) {
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    //jpa constructor
    public VehicleResponse() {
    }
    
    //setters
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    //getters
    public Long getVehicleId() {
        return vehicleId;
    }
    
    public String getRegistrationNumber() {
        return registrationNumber;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public String getModel() {
        return model;
    }
}