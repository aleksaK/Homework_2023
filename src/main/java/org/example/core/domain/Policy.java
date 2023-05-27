package org.example.core.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Policy {

    @Size(min = 1, message = "At least one bicycle is required")
    private List<@Valid Bicycle> bicycles;

    public Policy() {
    }

    public Policy(List<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

}