package com.insuranceApp.insuranceClient;

import com.insuranceApp.customObjects.RiskLevel;

public class InsuranceClientPriceLogic {
    private Integer priceIndex;
    private Integer price;

    public InsuranceClientPriceLogic(){}
    public InsuranceClientPriceLogic(RiskLevel ageRisk, RiskLevel healthRisk, RiskLevel jobRisk, RiskLevel livingAreaRisk){
        priceIndex=0;
        price=0;
        System.out.println(ageRisk);
        calculatePriceIndex(ageRisk);
        calculatePriceIndex(healthRisk);
        calculatePriceIndex(jobRisk);
        calculatePriceIndex(livingAreaRisk);
        calculatePrice();
    }
    private void calculatePriceIndex(RiskLevel risk) {
        switch (risk) {
            case LOW:
                this.priceIndex += 1;
                break;
            case MEDIUM:
                this.priceIndex += 2;
                break;
            case HIGH:
                this.priceIndex += 3;
                break;
        }
    }
    
    private void calculatePrice(){
        price = priceIndex*100;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
