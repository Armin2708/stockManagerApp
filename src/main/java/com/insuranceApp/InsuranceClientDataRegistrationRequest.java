package com.insuranceApp;

public record InsuranceClientDataRegistrationRequest(
         String firstName,
         String lastName,
         String email,
         String birthDate
) {
}
