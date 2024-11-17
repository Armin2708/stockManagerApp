package com.insuranceApp.requests;

import com.insuranceApp.customObjects.Gender;
import com.insuranceApp.customObjects.RiskLevel;

public record InsuranceClientUpdateRequest(
        String firstName,
        String lastName,
        Gender gender,
        String birthDate,
        String phoneNumber,
        String country,
        String state,
        String city,
        Integer postalCode,
        String street,
        RiskLevel ageRisk,
        RiskLevel healthRisk,
        RiskLevel jobRisk,
        RiskLevel livingAreaRisk
) {
}
