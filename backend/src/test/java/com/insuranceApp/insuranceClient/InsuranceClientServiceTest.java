package com.insuranceApp.insuranceClient;

import com.insuranceApp.requests.InsuranceClientRegistrationRequest;
import com.insuranceApp.requests.InsuranceClientUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.insuranceApp.customObjects.Gender.FEMALE;
import static com.insuranceApp.customObjects.Gender.MALE;
import static com.insuranceApp.customObjects.RiskLevel.LOW;
import static com.insuranceApp.customObjects.RiskLevel.MEDIUM;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class InsuranceClientServiceTest {

    @Mock
    private InsuranceClientDao clientDao;
    private InsuranceClientService underTest;

    @BeforeEach
    void setUp() {
        underTest = new InsuranceClientService(clientDao);
    }

    @Test
    void getAllClient() {
        //Given
        PageRequest pageable = PageRequest.of(0,2147483647);
        //When
        underTest.getAllClient(pageable);

        //Then
        verify(clientDao).getAllClient(pageable);
    }

    @Test
    void getClientById() {

        //Given
        Integer id = 5;

        InsuranceClient client = new InsuranceClient(
                id, "johndoe@mail.com","John","Doe", MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );
        when(clientDao.getClientById(id)).thenReturn(Optional.of(client));

        //When
        InsuranceClient actual = underTest.getClientById(id);

        //Then
        assertThat(actual).isEqualTo(client);

    }

    @Test
    void getClientByFirstName() {

        //Given
        String firstName = "John";
        String name = "John&";
        PageRequest pageable = PageRequest.of(0,2147483647);
        String page = "1&2147483647";

        InsuranceClient client = new InsuranceClient(
                5, "johndoe@mail.com",firstName,"Doe", MALE, "27/08/2003", "+3312345678",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );
        List<InsuranceClient> list = new ArrayList<>();
        list.add(client);

        Page<InsuranceClient> pageList = new PageImpl<>(list);
        when(clientDao.getClientByFirstName(firstName, pageable)).thenReturn(pageList);

        //When
        Page<InsuranceClient> actual = underTest.getClientByNameSearch(name,page);

        //Then
        assertThat(actual).isEqualTo(pageList);

    }

    @Test
    void getClientByLastName() {

        //Given
        String lastName = "Doe";
        String name = "&Doe";
        PageRequest pageable = PageRequest.of(0,2147483647);
        String page = "1&2147483647";

        InsuranceClient client = new InsuranceClient(
                5, "johndoe@mail.com","John",lastName, MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );
        List<InsuranceClient> list = new ArrayList<>();
        list.add(client);
        Page<InsuranceClient> pageList = new PageImpl<>(list);

        when(clientDao.getClientByLastName(lastName,pageable)).thenReturn(pageList);

        //When
        Page<InsuranceClient> actual = underTest.getClientByNameSearch(name,page);

        //Then
        assertThat(actual).isEqualTo(pageList);

    }

    @Test
    void getClientByFirstAndLastName() {

        //Given
        String firstName = "John";
        String lastName = "Doe";
        String name = "John&Doe";

        PageRequest pageable = PageRequest.of(0,2147483647);
        String page = "1&2147483647";

        InsuranceClient client = new InsuranceClient(
                5, "johndoe@mail.com",firstName,lastName, MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );
        List<InsuranceClient> list = new ArrayList<>();
        list.add(client);
        Page<InsuranceClient> pageList = new PageImpl<>(list);

        when(clientDao.getClientByFirstAndLastName(firstName,lastName,pageable)).thenReturn(pageList);

        //When
        Page<InsuranceClient> actual = underTest.getClientByNameSearch(name,page);

        //Then
        assertThat(actual).isEqualTo(pageList);

    }

    @Test
    void addClient() {

        //Given
        String email = "johndoe@mail.com";

        InsuranceClientRegistrationRequest request = new InsuranceClientRegistrationRequest(
                email,"John","Doe", MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        //When
        underTest.addClient(request);

        //Then
        ArgumentCaptor<InsuranceClient> clientArgumentCaptor = ArgumentCaptor.forClass(InsuranceClient.class);

        verify(clientDao).insertClient(clientArgumentCaptor.capture());

        InsuranceClient capturedClient = clientArgumentCaptor.getValue();

        assertThat(capturedClient.getEmail()).isEqualTo(request.email());
        assertThat(capturedClient.getFirstName()).isEqualTo(request.firstName());
        assertThat(capturedClient.getLastName()).isEqualTo(request.lastName());
        assertThat(capturedClient.getGender()).isEqualTo(request.gender());
        assertThat(capturedClient.getBirthDate()).isEqualTo(request.birthDate());
        assertThat(capturedClient.getPhoneNumber()).isEqualTo(request.phoneNumber());
        assertThat(capturedClient.getCountry()).isEqualTo(request.country());
        assertThat(capturedClient.getState()).isEqualTo(request.state());
        assertThat(capturedClient.getCity()).isEqualTo(request.city());
        assertThat(capturedClient.getPostalCode()).isEqualTo(request.postalCode());
        assertThat(capturedClient.getStreet()).isEqualTo(request.street());
        assertThat(capturedClient.getAgeRisk()).isEqualTo(request.ageRisk());
        assertThat(capturedClient.getHealthRisk()).isEqualTo(request.healthRisk());
        assertThat(capturedClient.getJobRisk()).isEqualTo(request.jobRisk());
        assertThat(capturedClient.getLivingAreaRisk()).isEqualTo(request.livingAreaRisk());

    }

    @Test
    void updateClient() {
        //Given
        String email = "johndoe@mail.com";
        Integer id = 5;

        InsuranceClient client = new InsuranceClient(
                id,email,"John","Doe", MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou", 78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );
        when(clientDao.getClientById(id)).thenReturn(Optional.of(client));


        InsuranceClientUpdateRequest updateRequest = new InsuranceClientUpdateRequest(
                "Jon","Wick", FEMALE, "23/02/2004", "+34612345678",
                "United States", "California", "Los Angeles", 90210, "18 Beach Walk Street",
                LOW,LOW,LOW,LOW);

        // When
        underTest.updateClient(id, updateRequest);

        // Then
        ArgumentCaptor<InsuranceClient> customerArgumentCaptor =
                ArgumentCaptor.forClass(InsuranceClient.class);

        verify(clientDao).updateClient(customerArgumentCaptor.capture());
        InsuranceClient capturedClient = customerArgumentCaptor.getValue();

        assertThat(capturedClient.getFirstName()).isEqualTo(updateRequest.firstName());
        assertThat(capturedClient.getLastName()).isEqualTo(updateRequest.lastName());
        assertThat(capturedClient.getGender()).isEqualTo(updateRequest.gender());
        assertThat(capturedClient.getBirthDate()).isEqualTo(updateRequest.birthDate());
        assertThat(capturedClient.getPhoneNumber()).isEqualTo(updateRequest.phoneNumber());
        assertThat(capturedClient.getCountry()).isEqualTo(updateRequest.country());
        assertThat(capturedClient.getState()).isEqualTo(updateRequest.state());
        assertThat(capturedClient.getCity()).isEqualTo(updateRequest.city());
        assertThat(capturedClient.getPostalCode()).isEqualTo(updateRequest.postalCode());
        assertThat(capturedClient.getStreet()).isEqualTo(updateRequest.street());
        assertThat(capturedClient.getAgeRisk()).isEqualTo(updateRequest.ageRisk());
        assertThat(capturedClient.getHealthRisk()).isEqualTo(updateRequest.healthRisk());
        assertThat(capturedClient.getJobRisk()).isEqualTo(updateRequest.jobRisk());
        assertThat(capturedClient.getLivingAreaRisk()).isEqualTo(updateRequest.livingAreaRisk());
    }

    @Test
    void deleteClient() {

        //Given
       Integer id = 5;

        when(clientDao.existClientById(id)).thenReturn(true);

        //When
        underTest.deleteClient(id);

        //Then
        verify(clientDao).deleteClientById(id);
    }
}