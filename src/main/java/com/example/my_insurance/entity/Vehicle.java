package main.java.com.example.my_insurance.entity;

import main.java.com.example.my_insurance.entity.Client;
import java.time.Year;

public class Vehicle {
    private final long vehicleId;
    private String registrationNumber;
    private String vin;
    private String manufacturer;
    private String model;
    private int manufacturingYear;
    
    private Client client;

    //constructor
    public Vehicle(long vehicleId, String registrationNumber, String vin, String manufacturer, 
                   String model, int manufacturingYear, Client client){
        this.vehicleId = vehicleId;
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.client = client;
    }

    //setters
    public void setRegistrationNumber(String registrationNumber){
        this.registrationNumber = registrationNumber; 
    }
    
    public void setVIN(String vin){
        this.vin = vin;
    }
    
    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }
    
    public void setModel(String model){
        this.model = model;
    }
    
    public void setManufacturingYear(int manufacturingYear){
        if(manufacturingYear <1885){
            throw new IllegalArgumentException("Manufacturing Year cannot be older than 1885");
        }
        else if (manufacturingYear > Year.now().getValue()){
            throw new IllegalArgumentException("Manufacturing Year cannot be newer than " + Year.now().getValue());
        }
        this.manufacturingYear = manufacturingYear;
    }
    
    public void setClient(Client client){
        this.client = client;
    }
        
    //getters
    public long getVehicleId(){
        return vehicleId; 
    }
    
    public String getRegistrationNumber(){
        return registrationNumber; 
    }
    
    public String getVIN(){
        return vin; 
    }
    
    public String getManufacturer(){
        return manufacturer; 
    }
    
    public String getModel(){
        return model; 
    }
    
    public int getManufacturingYear(){
        return manufacturingYear; 
    }
    
    public Client getClient(){
        return client; 
    }
    
    //object tostring
    @Override
    public String toString() {
        return "Vehicle => {" +
                "id='" + vehicleId + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", vin='" + vin + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +  
                ", manufacturingYear='" + manufacturingYear + '\'' + '\n'+
                ", " + client + '\'' +                 
                "}"
                ;
    }
    
    //equals and hashcode
        
    //methods
    //do vin n registration need a format check?
}

