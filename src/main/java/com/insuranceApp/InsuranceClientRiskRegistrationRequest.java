package com.insuranceApp;



public record InsuranceClientRiskRegistrationRequest(
         Integer age,
         Integer healthRisk,
         Integer jobRisk,
         Integer livingAreaRisk
) {
}
