package org.example.core.service;

import org.example.core.dao.AgeFactorsDAO;
import org.example.core.dao.RiskCountFactorsDAO;
import org.example.core.dao.SumInsuredFactorsDAO;
import org.example.core.domain.Bicycle;
import org.example.core.domain.Policy;
import org.example.core.domain.RiskType;
import org.example.core.dto.PremiumCalculationData;
import org.example.core.utils.GroovyScriptResolver;
import org.example.core.dao.RiskBasePremiumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PremiumCalculationService {

    private final GroovyScriptResolver scriptResolver;
    private final AgeFactorsDAO ageFactors;
    private final RiskBasePremiumDAO riskBasePremium;
    private final RiskCountFactorsDAO riskCountFactors;
    private final SumInsuredFactorsDAO sumInsuredFactors;

    @Autowired
    public PremiumCalculationService(GroovyScriptResolver scriptResolver, AgeFactorsDAO ageFactors,
                                     RiskBasePremiumDAO riskBasePremium, RiskCountFactorsDAO riskCountFactors,
                                     SumInsuredFactorsDAO sumInsuredFactors) {
        this.scriptResolver = scriptResolver;
        this.ageFactors = ageFactors;
        this.riskBasePremium = riskBasePremium;
        this.riskCountFactors = riskCountFactors;
        this.sumInsuredFactors = sumInsuredFactors;
    }

    public Double calculateTotalPremium(Policy policy) {
        return policy.getBicycles().stream()
                .map(this::calculateBicyclePremium)
                .reduce(0., Double::sum);
    }

    private Double calculateBicyclePremium(Bicycle bicycle) {
        return bicycle.getRisks().stream()
                .map(risk -> getCalculationData(bicycle, risk))
                .map(data -> (Double) scriptResolver.runMethodFromScript(
                        data.getRiskType().getRiskName() + "RiskPremiumCalc.groovy", "calculatePremium", data
                ))
                .reduce(0., Double::sum);
    }

    private PremiumCalculationData getCalculationData(Bicycle bicycle, RiskType risk) {
        return new PremiumCalculationData(
                risk,
                bicycle.getSumInsured(),
                LocalDateTime.now().getYear() - bicycle.getManufactureYear(),
                calculateRiskSumInsured(risk, bicycle.getSumInsured()),
                riskBasePremium.getRiskBasePremium(risk),
                riskCountFactors.getRiskCountFactors(bicycle.getRisks().size()),
                sumInsuredFactors.getSumInsuredFactors(bicycle.getSumInsured()),
                ageFactors.getAgeFactors(bicycle)
        );
    }

    private BigDecimal calculateRiskSumInsured(RiskType risk, BigDecimal objectSumInsured) {
        return (BigDecimal) scriptResolver.runMethodFromScript(
                risk.getRiskName() + "RiskSumInsuredCalc.groovy",
                "getRiskSumInsured",
                objectSumInsured);
    }

}