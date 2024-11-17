package com.insuranceApp.insuranceClient;

import com.insuranceApp.clientFile.ClientFileDTO;
import com.insuranceApp.customObjects.Gender;
import com.insuranceApp.customObjects.RiskLevel;

import java.util.List;
import java.util.stream.Collectors;

public class InsuranceClientDTO {
    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String birthDate;

    private String phoneNumber;

    private String country;

    private String state;

    private String city;

    private Integer postalCode;

    private String street;

    private RiskLevel ageRisk;

    private RiskLevel healthRisk;

    private RiskLevel jobRisk;

    private RiskLevel livingAreaRisk;

    private List<ClientFileDTO> files;

    public InsuranceClientDTO(InsuranceClient client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.gender = client.getGender();
        this.birthDate = client.getBirthDate();
        this.phoneNumber = client.getPhoneNumber();
        this.country = client.getCountry();
        this.state = client.getState();
        this.city = client.getCity();
        this.postalCode = client.getPostalCode();
        this.street = client.getStreet();
        this.ageRisk = client.getAgeRisk();
        this.healthRisk = client.getHealthRisk();
        this.jobRisk = client.getJobRisk();
        this.livingAreaRisk = client.getLivingAreaRisk();
        this.files = client.getFiles().stream()
                .map(file -> new ClientFileDTO(file.getId(), file.getFileName(), file.getFileSize()))
                .collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public RiskLevel getAgeRisk() {
        return ageRisk;
    }

    public void setAgeRisk(RiskLevel ageRisk) {
        this.ageRisk = ageRisk;
    }

    public RiskLevel getHealthRisk() {
        return healthRisk;
    }

    public void setHealthRisk(RiskLevel healthRisk) {
        this.healthRisk = healthRisk;
    }

    public RiskLevel getJobRisk() {
        return jobRisk;
    }

    public void setJobRisk(RiskLevel jobRisk) {
        this.jobRisk = jobRisk;
    }

    public RiskLevel getLivingAreaRisk() {
        return livingAreaRisk;
    }

    public void setLivingAreaRisk(RiskLevel livingAreaRisk) {
        this.livingAreaRisk = livingAreaRisk;
    }

    public List<ClientFileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<ClientFileDTO> files) {
        this.files = files;
    }
}