package com.insuranceApp.insuranceClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InsuranceClientDao {
    //Create
    void insertClient(InsuranceClient client);

    //Read
    List<InsuranceClient> getAllClient(Integer pageNumber);

    Optional<InsuranceClient> getClientById(Integer id);
    boolean existClientByEmail(String email);
    boolean existClientById(Integer id);

    List<InsuranceClient> getClientByFirstAndLastName(String firstName, String lastName, Integer pageNumber);
    List<InsuranceClient> getClientByFirstName(String firstName, Integer pageNumber);
    List<InsuranceClient> getClientByLastName(String lastName, Integer pageNumber);

    //Update
    void updateClient(InsuranceClient update);

    //Delete

    void deleteClientById(Integer id);


}
