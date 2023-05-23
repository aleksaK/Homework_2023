package org.example.core.dto;

import org.example.core.domain.RiskType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class PremiumCalculationData {

    private final RiskType riskType;
    private final BigDecimal objectSumInsured;
    private final int ageActual;
    private final BigDecimal riskSumInsured;
    private final BigDecimal riskBasePremium;
    private final LinkedHashMap<String, Serializable> riskCountFactors;
    private final LinkedHashMap<String, Serializable> sumInsuredFactors;
    private final LinkedHashMap<String, Serializable> ageFactors;


    public PremiumCalculationData(
            RiskType riskType, BigDecimal objectSumInsured, int ageActual, BigDecimal riskSumInsured,
            BigDecimal riskBasePremium,
            LinkedHashMap<String, Serializable> riskCountFactors, LinkedHashMap<String, Serializable> sumInsuredFactors,
            LinkedHashMap<String, Serializable> ageFactors
    ) {
        this.riskType = riskType;
        this.objectSumInsured = objectSumInsured;
        this.ageActual = ageActual;
        this.riskSumInsured = riskSumInsured;
        this.riskBasePremium = riskBasePremium;
        this.riskCountFactors = riskCountFactors;
        this.sumInsuredFactors = sumInsuredFactors;
        this.ageFactors = ageFactors;
    }

    public BigDecimal getObjectSumInsured() {
        return objectSumInsured;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public int getAgeActual() {
        return ageActual;
    }

    public BigDecimal getRiskSumInsured() {
        return riskSumInsured;
    }

    public BigDecimal getRiskBasePremium() {
        return riskBasePremium;
    }

    public LinkedHashMap<String, Serializable> getRiskCountFactors() {
        return riskCountFactors;
    }

    public LinkedHashMap<String, Serializable> getSumInsuredFactors() {
        return sumInsuredFactors;
    }

    public LinkedHashMap<String, Serializable> getAgeFactors() {
        return ageFactors;
    }

    @Override
    public String toString() {
        return "PremiumCalculationData{" +
                "riskType=" + riskType +
                ", objectSumInsured=" + objectSumInsured +
                ", ageActual=" + ageActual +
                ", riskSumInsured=" + riskSumInsured +
                ", riskBasePremium=" + riskBasePremium +
                ", riskCountFactors=" + riskCountFactors +
                ", sumInsuredFactors=" + sumInsuredFactors +
                ", ageFactors=" + ageFactors +
                '}';
    }

}