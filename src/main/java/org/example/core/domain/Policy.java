package org.example.core.domain;

import java.util.List;

public class Policy {

    private List<Bicycle> bicycles;

    public Policy() {
    }

    public Policy(List<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

}