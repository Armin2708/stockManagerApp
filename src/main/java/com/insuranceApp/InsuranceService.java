package com.insuranceApp;

public class InsuranceService {
    public void addClient(InsuranceClientDataRegistrationRequest dataRequest,
                     InsuranceClientAddressRegistrationRequest addressRequest,
                     InsuranceClientRiskRegistrationRequest riskRequest){

        InsuranceClientAddress address = new InsuranceClientAddress(addressRequest.country(),addressRequest.state(),addressRequest.city(),addressRequest.postalCode(),addressRequest.streetNumber(),addressRequest.streetName());
        InsuranceClientRisk risk = new InsuranceClientRisk(riskRequest.age(),riskRequest.healthRisk(),riskRequest.jobRisk(),riskRequest.livingAreaRisk());
        InsuranceClientPrice price = new InsuranceClientPrice(risk);
        InsuranceClient client = new InsuranceClient(dataRequest.firstName(),dataRequest.lastName(),dataRequest.email(),dataRequest.birthDate(), address, risk, price);



    }
}
