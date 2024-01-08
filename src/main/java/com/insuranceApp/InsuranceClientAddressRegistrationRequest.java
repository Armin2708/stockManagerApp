package com.insuranceApp;

public record InsuranceClientAddressRegistrationRequest(
         String country,
         String state,
         String city,
         Integer postalCode,
         Integer streetNumber,
         String streetName
) {
}
