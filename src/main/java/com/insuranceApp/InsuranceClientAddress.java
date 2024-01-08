package com.insuranceApp;

public class InsuranceClientAddress {

    private String country;
    private String state;
    private String city;
    private Integer postalCode;
    private Integer streetNumber;
    private String streetName;
    private String fullAddress;

    public InsuranceClientAddress(){}
    public InsuranceClientAddress(String country, String state, String city, Integer postalCode,
                                 Integer streetNumber, String streetName){
        this.country=country;
        this.state=state;
        this.city=city;
        this.postalCode=postalCode;
        this.streetNumber=streetNumber;
        this.streetName=streetName;
        this.fullAddress = country + " " + state+ " " + city+ " " + postalCode+ " " + streetNumber+ " " + streetName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
