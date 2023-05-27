package org.example.core.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Bicycle {

    @NotBlank(message = "Make is required")
    private final String make;

    @NotBlank(message = "Model is required")
    private final String model;

    @Min(value = 1900, message = "Minimum manufacture year is 1900")
    @Max(value = 2023, message = "Maximum manufacture year is current year")
    private final int manufactureYear;

    @Min(value = 100, message = "Minimum bicycle insurable amount is 100.00")
    @Max(value = 5000, message = "Maximum bicycle insurable amount is 5000.00")
    private final BigDecimal sumInsured;

    @Size(min = 1, message = "At least one risk must be insured")
    private final List<RiskType> risks;

}