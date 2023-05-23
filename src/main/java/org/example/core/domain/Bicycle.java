package org.example.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class Bicycle {

    private String make;
    private String model;
    private int manufactureYear;
    private BigDecimal sumInsured;
    private List<RiskType> risks;

    public Bicycle() {
    }

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