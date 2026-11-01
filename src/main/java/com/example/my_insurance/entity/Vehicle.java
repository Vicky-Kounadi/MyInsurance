package com.example.my_insurance.entity;

import java.time.Year;
import jakarta.persistence.*;

@Entity
@Table(name="vehicle")
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vehicle_id")
    private Long vehicleId;
    
    @Column(name="registration_number", nullable=false, unique=true, length = 20)
    private String registrationNumber;
    
    @Column(name="vin", nullable=false, unique=true, length = 17)
    private String vin;
    
    @Column(name="manufacturer", nullable=false, length = 50)
    private String manufacturer;
    
    @Column(name="model", nullable=false, length = 50)
    private String model;
    
    @Column(name="manufacturing_year")
    private int manufacturingYear;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    //constructor
    public Vehicle(String registrationNumber, String vin, String manufacturer, 
                   String model, int manufacturingYear, Client client){
        this.registrationNumber = registrationNumber;
        this.vin = vin;
        this.manufacturer = manufacturer;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.client = client;
    }
    
    //JPA constructor
    protected Vehicle(){   
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
    public Long getVehicleId(){
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

