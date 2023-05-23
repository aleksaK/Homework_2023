package org.example.core.domain;

import java.util.List;

public class Policy {

    private final List<Bicycle> bicycles;

    public Policy(List<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

}