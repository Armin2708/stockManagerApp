package com.insuranceApp.insuranceClient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insuranceApp.clientFile.ClientFile;
import com.insuranceApp.customObjects.Gender;
import com.insuranceApp.customObjects.RiskLevel;
import jakarta.persistence.*;
import com.github.javafaker.Faker;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

@Entity
@Table(
        name = "client",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "email"
                )
        })
public class InsuranceClient {

    @Id
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    private Integer id;

    @Column(name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(name = "gender",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date",
            nullable = false
    )
    private String birthDate;

    @Column(name = "phone_number",
            nullable = false
    )
    private String phoneNumber;

    @Column(name = "country",
            nullable = false
    )
    private String country;

    @Column(name = "state",
            nullable = false
    )
    private String state;
    @Column(name = "city",
            nullable = false
    )
    private String city;

    @Column(name = "postal_code",
            nullable = false
    )
    private Integer postalCode;

    @Column(name = "street",
            nullable = false
    )
    private String street;

    @Column(name = "age_risk",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private RiskLevel ageRisk;

    @Column(name = "health_risk",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private RiskLevel healthRisk;

    @Column(name = "job_risk",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private RiskLevel jobRisk;

    @Column(name = "living_area_risk",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private RiskLevel livingAreaRisk;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<ClientFile> files;


    private Integer price;

    public InsuranceClient(){}

    public InsuranceClient(Integer id, String email, String firstName, String lastName,Gender gender, String birthDate,String phoneNumber,
                           String country, String state, String city, Integer postalCode,String street,
                           RiskLevel ageRisk, RiskLevel healthRisk, RiskLevel jobRisk, RiskLevel livingAreaRisk
    ){
        this.id = id;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.birthDate=birthDate;
        this.phoneNumber=phoneNumber;

        this.country=country;
        this.state=state;
        this.city=city;
        this.postalCode=postalCode;
        this.street=street;

        this.ageRisk=ageRisk;
        this.healthRisk=healthRisk;
        this.jobRisk=jobRisk;
        this.livingAreaRisk=livingAreaRisk;
    }

    public InsuranceClient(String email, String firstName, String lastName,Gender gender, String birthDate,String phoneNumber,
                           String country, String state, String city, Integer postalCode,String street,
                           RiskLevel ageRisk, RiskLevel healthRisk, RiskLevel jobRisk, RiskLevel livingAreaRisk
    ){
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.birthDate=birthDate;
        this.phoneNumber=phoneNumber;

        this.country=country;
        this.state=state;
        this.city=city;
        this.postalCode=postalCode;
        this.street=street;

        this.ageRisk=ageRisk;
        this.healthRisk=healthRisk;
        this.jobRisk=jobRisk;
        this.livingAreaRisk=livingAreaRisk;
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

    public void setEmail(String email){
        this.email=email;
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

    public List<ClientFile> getFiles() {
        return files;
    }

    public void setFiles(List<ClientFile> files) {
        this.files = files;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static InsuranceClient generateRandomClient() {
            Faker faker = new Faker(new Locale("en"));
            InsuranceClient client = new InsuranceClient();

            client.setId(faker.number().randomDigit());
            client.setEmail(faker.internet().emailAddress());
            client.setFirstName(faker.name().firstName());
            client.setLastName(faker.name().lastName());
            client.setGender(Gender.valueOf(faker.options().option("FEMALE", "MALE")));
            client.setBirthDate(String.valueOf(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            client.setPhoneNumber("+33610203040");
            client.setCountry(faker.address().country());
            client.setState(faker.address().state());
            client.setCity(faker.address().city());
            client.setPostalCode(78400);
            client.setStreet(faker.address().streetAddress());
            client.setAgeRisk(RiskLevel.LOW);
            client.setHealthRisk(RiskLevel.LOW);
            client.setJobRisk(RiskLevel.LOW);
            client.setLivingAreaRisk(RiskLevel.LOW);

            return client;
    }
}
