package com.insuranceApp;

public class InsuranceClient {

    private String firstName;
    private String lastName;
    private String email;
    private Integer id;
    private String birthDate;
    private String address;
    private String risk;
    private Integer insurancePrice;

    public InsuranceClient(){}
    public InsuranceClient(String firstName, String lastName,String email, String birthDate){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.birthDate=birthDate;
    }
    public InsuranceClient(String firstName, String lastName,String email, String birthDate, InsuranceClientAddress address, InsuranceClientRisk risk, InsuranceClientPrice price){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.birthDate=birthDate;

        this.address=address.getFullAddress();
        this.risk=risk.getRisk();
        this.insurancePrice= price.getPrice();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


}
