package com.insuranceApp.insuranceClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InsuranceClientRepository extends JpaRepository<InsuranceClient, Integer> {

    boolean existsInsuranceClientByEmail(String email);

    Page<InsuranceClient> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
    Page<InsuranceClient> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
    Page<InsuranceClient> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

    List<InsuranceClient> findAll();

    boolean existsInsuranceClientById(Integer id);
    void deleteInsuranceClientById(Integer id);

}
