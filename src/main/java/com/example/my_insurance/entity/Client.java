package main.java.com.example.my_insurance.entity;

public class Client {
    private final long clientId;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;

    //constructor
    public Client(long clientId, String lastName, String firstName, String email, String phone){
        this.clientId = clientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
    }

    //setters
    public void setLastName(String lastName){
        this.lastName = lastName; 
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
        
    //getters
    public long getClientId(){
        return clientId; 
    }
    
    public String getLastName(){
        return lastName; 
    }
    
    public String getFirstName(){
        return firstName; 
    }
    
    public String getEmail(){
        return email; 
    }
    
    public String getPhone(){
        return phone; 
    }
    
    //object tostring
    @Override
    public String toString() {
        return "Client => {" +
                "id='" + clientId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +                    
                "}"
                ;
    }
    
    //equals and hashcode
    
    //methods
    //must add a phone number and email checker function and use it in the setters
}

