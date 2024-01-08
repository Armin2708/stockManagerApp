package com.insuranceApp;

public class InsuranceClientPrice {
    private Integer price;

    public InsuranceClientPrice(InsuranceClientRisk risks){
        Integer priceCalculation=0;
        switch (risks.getAgeRisk()){
            case LOW -> priceCalculation+=1;
            case MEDIUM -> priceCalculation+=2;
            case HIGH -> priceCalculation+=3;
        }
        switch (risks.getHealthRisk()){
            case LOW -> priceCalculation+=1;
            case MEDIUM -> priceCalculation+=2;
            case HIGH -> priceCalculation+=3;
        }
        switch (risks.getJobRisk()){
            case LOW -> priceCalculation+=1;
            case MEDIUM -> priceCalculation+=2;
            case HIGH -> priceCalculation+=3;
        }
        switch (risks.getLivingAreaRisk()){
            case LOW -> priceCalculation+=1;
            case MEDIUM -> priceCalculation+=2;
            case HIGH -> priceCalculation+=3;
        }

        priceCalculation=priceCalculation*10;
        this.price=priceCalculation;
        
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
