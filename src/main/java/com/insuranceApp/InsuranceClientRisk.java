package com.insuranceApp;

import com.insuranceApp.exceptions.InvalidFormValue;

public class InsuranceClientRisk {
    private Risk ageRisk;
    private Risk healthRisk;
    private Risk jobRisk;
    private Risk livingAreaRisk;
    private String risk;

    public InsuranceClientRisk(){}

    public InsuranceClientRisk(Integer age, Integer healthRiskScale, Integer jobRiskScale, Integer livingAreaScale){
        askAgeRisk(age);
        askHealthRisk(healthRiskScale);
        askJobRisk(jobRiskScale);
        askLivingAreaRisk(livingAreaScale);

        this.risk= ageRisk+" "+healthRisk+" "+jobRisk+" "+livingAreaRisk;
    }
    public void askAgeRisk(Integer age){
        if (age>=18 && age<=30){
            setAgeRisk(Risk.LOW);
        } else if (age>=31 && age<=45) {
            setAgeRisk(Risk.MEDIUM);
        } else if (age>=45) {
            setAgeRisk(Risk.HIGH);
        } else {
            throw new InvalidFormValue("age has to be between at least 18");
        }
    }
    public void askHealthRisk(Integer healthRiskScale){
        if (healthRiskScale>=0 && healthRiskScale<=3){
            setHealthRisk(Risk.LOW);
        } else if (healthRiskScale>=4 && healthRiskScale<=7) {
            setHealthRisk(Risk.MEDIUM);
        } else if (healthRiskScale>=8 && healthRiskScale<=10) {
            setHealthRisk(Risk.HIGH);
        } else {
            throw new InvalidFormValue("Value has to be between 0-10");
        }
    }
    public void askJobRisk(Integer jobRiskScale){
        if (jobRiskScale>=0 && jobRiskScale<=3){
            setJobRisk(Risk.LOW);
        } else if (jobRiskScale>=4 && jobRiskScale<=7) {
            setJobRisk(Risk.MEDIUM);
        } else if (jobRiskScale>=8 && jobRiskScale<=10) {
            setJobRisk(Risk.HIGH);
        } else {
            throw new InvalidFormValue("Value has to be between 0-10");
        }
    }
    public void askLivingAreaRisk(Integer livingAreaScale){
        if (livingAreaScale>=0 && livingAreaScale<=3){
            setLivingAreaRisk(Risk.LOW);
        } else if (livingAreaScale>=4 && livingAreaScale<=7) {
            setLivingAreaRisk(Risk.MEDIUM);
        } else if (livingAreaScale>=8 && livingAreaScale<=10) {
            setLivingAreaRisk(Risk.HIGH);
        } else {
            throw new InvalidFormValue("Value has to be between 0-10");
        }
    }

    public Risk getAgeRisk() {
        return ageRisk;
    }

    public void setAgeRisk(Risk ageRisk) {
        this.ageRisk = ageRisk;
    }

    public Risk getHealthRisk() {
        return healthRisk;
    }

    public void setHealthRisk(Risk healthRisk) {
        this.healthRisk = healthRisk;
    }

    public Risk getJobRisk() {
        return jobRisk;
    }

    public void setJobRisk(Risk jobRisk) {
        this.jobRisk = jobRisk;
    }

    public Risk getLivingAreaRisk() {
        return livingAreaRisk;
    }

    public void setLivingAreaRisk(Risk livingAreaRisk) {
        this.livingAreaRisk = livingAreaRisk;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
