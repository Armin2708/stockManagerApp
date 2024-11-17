package com.insuranceApp.insuranceClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.insuranceApp.customObjects.Gender.MALE;
import static com.insuranceApp.customObjects.RiskLevel.MEDIUM;

import static org.mockito.Mockito.verify;

class InsuranceClientJPADataAccessTest {
    private InsuranceClientJPADataAccess underTest;
    private AutoCloseable autoCloseable;
    @Mock
    private InsuranceClientRepository insuranceClientRepository;

    @BeforeEach
    void setUp(){
        autoCloseable= MockitoAnnotations.openMocks(this);
        underTest = new InsuranceClientJPADataAccess(insuranceClientRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void insertClient() {
        //Before
        InsuranceClient client = new InsuranceClient(
                1,"johndoe@mail.com","John","Doe", MALE, "27/08/2003", "+33123456789",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );
        //When
        underTest.insertClient(client);

        //Then
        verify(insuranceClientRepository).save(client);

    }

    @Test
    void getAllClient() {
        //Before
        PageRequest pageable = PageRequest.of(0,2147483647);
        //When
        underTest.getAllClient(pageable);

        //Then
        verify(insuranceClientRepository).findAll(pageable);

    }

    @Test
    void getClientByFirstName() {
        //Before
       String firstName = "John";
        PageRequest pageable = PageRequest.of(0,Integer.MAX_VALUE);

        //When
        underTest.getClientByFirstName(firstName,pageable);

        //Then
        verify(insuranceClientRepository).findByFirstNameContainingIgnoreCase(firstName,pageable);

    }

    @Test
    void getClientByLastName() {
        //Before
        String lastName = "Doe";
        PageRequest pageable = PageRequest.of(0,Integer.MAX_VALUE);
        //When
        underTest.getClientByLastName(lastName,pageable);

        //Then
        verify(insuranceClientRepository).findByLastNameContainingIgnoreCase(lastName,pageable);

    }

    @Test
    void getClientByFirstAndLastName() {
        //Before
        String firstName = "John";
        String lastName = "Doe";
        PageRequest pageable = PageRequest.of(0,Integer.MAX_VALUE);

        //When
        underTest.getClientByFirstAndLastName(firstName,lastName,pageable);

        //Then
        verify(insuranceClientRepository).findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName,lastName,pageable);

    }

    @Test
    void getClientById() {
        //Before
        Integer id = 5;

        //When
        underTest.getClientById(id);

        //Then
        verify(insuranceClientRepository).findById(id);

    }


    @Test
    void existClientByEmail() {
        //Before
        String email="johndoe@mail.com";

        //When
        underTest.existClientByEmail(email);

        //Then
        verify(insuranceClientRepository).existsInsuranceClientByEmail(email);

    }

    @Test
    void existClientById() {
        //Before
        Integer id = 5;

        //When
        underTest.existClientById(id);

        //Then
        verify(insuranceClientRepository).existsInsuranceClientById(id);

    }

    @Test
    void updateClient() {
        //Before
        InsuranceClient client = new InsuranceClient(
                1,"johndoe@mail.com","John","Doe", MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        //When
        underTest.updateClient(client);

        //Then
        verify(insuranceClientRepository).save(client);

    }

    @Test
    void deleteClientById() {
        //Before
        Integer id = 5;

        //When
        underTest.deleteClientById(id);

        //Then
        verify(insuranceClientRepository).deleteInsuranceClientById(id);

    }
}