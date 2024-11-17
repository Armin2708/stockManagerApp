package com.insuranceApp.journey;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.insuranceApp.customObjects.Gender;
import com.insuranceApp.customObjects.RiskLevel;
import com.insuranceApp.insuranceClient.*;
import com.insuranceApp.requests.InsuranceClientRegistrationRequest;
import com.insuranceApp.requests.InsuranceClientUpdateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.insuranceApp.customObjects.Gender.MALE;
import static com.insuranceApp.customObjects.RiskLevel.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@SpringBootTest(webEnvironment = RANDOM_PORT)
public class InsuranceClientIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    private static final Random RANDOM = new Random();
    private static final String CLIENT_URI = "/insuranceApp";

    @Test
    void canRegisterClient() {
        // create registration request

        Faker faker = new Faker();
        Name fakerName = faker.name();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@mail.com";

        InsuranceClientRegistrationRequest request = new InsuranceClientRegistrationRequest(
                email,"John","Doe", MALE, "08/27/2003", "+33612345678",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );
        // send a post request
        webTestClient.post()
                .uri(CLIENT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), InsuranceClientRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // get all clients
        int page = 0;
        int size = 2000;
        List<InsuranceClient> allClients = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(CLIENT_URI+"/getAll")
                        .queryParam("page",page)
                        .queryParam("size",size)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<InsuranceClient>() {
                })
                .returnResult()
                .getResponseBody();

        // make sure that client is present
        InsuranceClient expectedClient = new InsuranceClient(
                email,"John","Doe", MALE, "08/27/2003", "+33612345678",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        assertThat(allClients).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id").contains(expectedClient);

        int id = allClients.stream()
                .filter(client -> client.getEmail().equals(email))
                .map(InsuranceClient::getId)
                .findFirst()
                .orElseThrow();

        expectedClient.setId(id);

        // get client by id
        webTestClient.get()
                .uri(CLIENT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<InsuranceClient>() {
                })
                .equals(expectedClient);
    }
    @Test
    void canDeleteClient(){
        // create registration request
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@mail.com";

        InsuranceClientRegistrationRequest request = new InsuranceClientRegistrationRequest(
                email,"John","Doe", MALE, "08/27/2003", "+3312345678",
                "France","Yvelines","Chatou",78400,"15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        // send a post request
        webTestClient.post()
                .uri(CLIENT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), InsuranceClientRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // get all clients
        List<InsuranceClient> allClient = webTestClient.get()
                .uri(CLIENT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<InsuranceClient>() {
                })
                .returnResult()
                .getResponseBody();

        int id = allClient.stream()
                .filter(client -> client.getEmail().equals(email))
                .map(InsuranceClient::getId)
                .findFirst()
                .orElseThrow();


        //delete client
        webTestClient.delete()
                .uri(CLIENT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        // get client by id
        webTestClient.get()
                .uri(CLIENT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    void canUpdateClient(){
        // create registration request
        Faker faker = new Faker();
        Name fakerName = faker.name();

        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@mail.com";
        String firstName = "John";
        String lastName = "Doe";
        Gender gender = MALE;
        String birthDate = "08/27/2003";
        String phoneNumber = "+33612345678";
        String country= "France";
        String state = "Yvelines";
        String city = "Chatou";
        Integer postalCode = 78400;
        String street = "15 Rue Auguste Renoir";
        RiskLevel ageRisk = MEDIUM;
        RiskLevel healthRisk = MEDIUM;
        RiskLevel jobRisk = MEDIUM;
        RiskLevel livingAreaRisk = MEDIUM;

        InsuranceClientRegistrationRequest request = new InsuranceClientRegistrationRequest(
                email,firstName,lastName, gender, birthDate, phoneNumber,
                country,state,city,postalCode,street,
                ageRisk, healthRisk, jobRisk, livingAreaRisk
        );

        // send a post request
        webTestClient.post()
                .uri(CLIENT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), InsuranceClientRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        // get all clients
        List<InsuranceClient> allClient = webTestClient.get()
                .uri(CLIENT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<InsuranceClient>() {
                })
                .returnResult()
                .getResponseBody();

        int id = allClient.stream()
                .filter(client -> client.getEmail().equals(email))
                .map(InsuranceClient::getId)
                .findFirst()
                .orElseThrow();



        //update client

        String newName = "Joe";
        InsuranceClientUpdateRequest updateRequest = new InsuranceClientUpdateRequest(
                newName,null,null, null,null,
                null,null,null,null,null,null,
                null,null,null);

        webTestClient.put()
                .uri(CLIENT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateRequest),InsuranceClientUpdateRequest.class)
                .exchange()
                .expectStatus()
                .isOk();

        InsuranceClient expected = new InsuranceClient(id,email, newName,lastName, gender, birthDate, phoneNumber,
                country,state,city,postalCode,street,
                ageRisk, healthRisk, jobRisk, livingAreaRisk);

        // get client by id
        webTestClient.get()
                .uri(CLIENT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<InsuranceClient>() {
                })
                .equals(expected);
    }

}

