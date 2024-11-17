package com.insuranceApp.insuranceClient;

import com.insuranceApp.AbstractTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

import static com.insuranceApp.customObjects.Gender.MALE;
import static com.insuranceApp.customObjects.RiskLevel.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InsuranceClientRepositoryTest extends AbstractTestContainer {

    @Autowired
    private InsuranceClientRepository underTest;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp(){
        underTest.deleteAll();
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    @Test
    void existsInsuranceClientByEmail(){

        //Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        InsuranceClient insuranceClient = new InsuranceClient(
                email,FAKER.name().firstName(), FAKER.name().lastName(), MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        underTest.save(insuranceClient);

        //When
        var actual = underTest.existsInsuranceClientByEmail(email);

        //Then
        assertThat(actual).isTrue();
    }

    @Test
    void findByFirstNameContainingAndLastNameContaining(){

        //Given
        PageRequest pageable = PageRequest.of(0,2147483647);
        String firstName = "John";
        String lastName = "Doe";
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        InsuranceClient insuranceClient = new InsuranceClient(
                email,firstName, lastName, MALE, "27/08/2003", "+3312345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        underTest.save(insuranceClient);

        //When
        var actual = underTest.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName,pageable);

        //Then
        assertThat(actual).contains(insuranceClient);
    }
    @Test
    void findByFirstNameContaining(){

        //Given
        PageRequest pageable = PageRequest.of(0,2147483647);
        String firstName = "John";
        String lastName = "Doe";
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        InsuranceClient insuranceClient = new InsuranceClient(
                email,firstName, lastName, MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        underTest.save(insuranceClient);

        //When
        var actual = underTest.findByFirstNameContainingIgnoreCase(firstName,pageable);

        //Then
        assertThat(actual).contains(insuranceClient);
    }

    @Test
    void findByLastNameContaining(){

        //Given
        PageRequest pageable = PageRequest.of(0,2147483647);
        String firstName = "John";
        String lastName = "Doe";
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        InsuranceClient insuranceClient = new InsuranceClient(
                email,firstName, lastName, MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        underTest.save(insuranceClient);

        //When
        var actual = underTest.findByLastNameContainingIgnoreCase(lastName,pageable);

        //Then
        assertThat(actual).contains(insuranceClient);
    }

    @Test
    void existsInsuranceClientById(){

        //Given
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        InsuranceClient insuranceClient = new InsuranceClient(
                email,FAKER.name().firstName(), FAKER.name().lastName(), MALE, "27/08/2003", "+3312345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        underTest.save(insuranceClient);

        int id = underTest.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(InsuranceClient::getId)
                .findFirst()
                .orElseThrow();

        //When
        var actual = underTest.existsInsuranceClientById(id);

        //Then
        assertThat(actual).isTrue();
    }

    @Test
    void deleteInsuranceClientById(){

        //Given
        Integer id = 5;
        String email = FAKER.internet().safeEmailAddress() + "_" + UUID.randomUUID();
        InsuranceClient insuranceClient = new InsuranceClient(
                email,FAKER.name().firstName(), FAKER.name().lastName(), MALE, "27/08/2003", "+33612345678",
                "France","Yvelines","Chatou",78400, "15 Rue Auguste Renoir",
                MEDIUM, MEDIUM, MEDIUM, MEDIUM
        );

        underTest.save(insuranceClient);

        //When
        underTest.deleteInsuranceClientById(id);
        var actual = underTest.existsInsuranceClientById(id);

        //Then
        assertThat(actual).isFalse();

    }

}
