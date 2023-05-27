package org.example.core.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.RiskType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

@Data
@RequiredArgsConstructor
public class PremiumCalculationData {

    private final RiskType riskType;
    private final BigDecimal objectSumInsured;
    private final int ageActual;
    private final BigDecimal riskSumInsured;
    private final BigDecimal riskBasePremium;
    private final LinkedHashMap<String, Serializable> riskCountFactors;
    private final LinkedHashMap<String, Serializable> sumInsuredFactors;
    private final LinkedHashMap<String, Serializable> ageFactors;

}