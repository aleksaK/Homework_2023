package org.example.core.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class Bicycle {

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @Min(value = 1900, message = "Minimum manufacture year is 1900")
    @Max(value = 2023, message = "Maximum manufacture year is current year")
    private int manufactureYear;

    @Min(value = 100, message = "Minimum bicycle insurable amount is 100.00")
    @Max(value = 5000, message = "Maximum bicycle insurable amount is 5000.00")
    private BigDecimal sumInsured;

    @Size(min = 1, message = "At least one risk must be insured")
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