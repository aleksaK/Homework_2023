package org.example.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class Bicycle {

    private final String make;
    private final String model;
    private final int manufactureYear;
    private final BigDecimal sumInsured;
    private final List<RiskType> risks;

    public Bicycle(String make, String model, int manufactureYear, BigDecimal sumInsured, List<RiskType> risks) {
        this.make = make;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.sumInsured = sumInsured;
        this.risks = risks;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public List<RiskType> getRisks() {
        return risks;
    }

}