package com.insuranceApp.insuranceClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("insuranceClientJpa")
public class InsuranceClientJPADataAccess implements InsuranceClientDao {

    private final InsuranceClientRepository clientRepository;

    public InsuranceClientJPADataAccess(InsuranceClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public void insertClient(InsuranceClient client) {
        clientRepository.save(client);
    }


    public List<InsuranceClient> getAllClient(Integer pageNumber) {
        Pageable pageable =  PageRequest.of(pageNumber, 10);
        Page page = clientRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public Optional<InsuranceClient> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    public boolean existClientByEmail(String email) {
        return clientRepository.existsInsuranceClientByEmail(email);
    }

    @Override
    public boolean existClientById(Integer id) {
        return clientRepository.existsInsuranceClientById(id);
    }

    @Override
    public List<InsuranceClient> getClientByFirstAndLastName(String firstName, String lastName, Integer pageNumber) {
        Pageable pageable =  PageRequest.of(pageNumber, 10);
        Page page = clientRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName,lastName, pageable);
        return page.getContent();
    }

    @Override
    public List<InsuranceClient> getClientByFirstName(String firstName, Integer pageNumber) {
        Pageable pageable =  PageRequest.of(pageNumber, 10);
        Page page = clientRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);
        return page.getContent();
    }

    @Override
    public List<InsuranceClient> getClientByLastName(String lastName, Integer pageNumber) {
        Pageable pageable =  PageRequest.of(pageNumber, 10);
        Page page = clientRepository.findByLastNameContainingIgnoreCase(lastName,pageable);
        return page.getContent();
    }


    public void updateClient(InsuranceClient update) {
        clientRepository.save(update);
    }

    @Override
    public void deleteClientById(Integer id) {
        clientRepository.deleteInsuranceClientById(id);
    }


}
