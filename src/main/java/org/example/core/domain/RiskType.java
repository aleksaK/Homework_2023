package org.example.core.domain;

public enum RiskType {

    THEFT("Theft"), DAMAGE("Damage"), THIRD_PARTY_DAMAGE("ThirdPartyDamage");

    private final String riskName;

    RiskType(String value) {
        riskName = value;
    }

    public String getRiskName() {
        return riskName;
    }

}