package org.example.web;

import org.example.core.domain.Policy;
import org.example.core.service.PremiumCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculatorController {

    final private PremiumCalculationService service;

    @Autowired
    public CalculatorController(PremiumCalculationService service) {
        this.service = service;
    }

    @PostMapping(path = "/calculator/",
            consumes = "application/json",
            produces = "application/json")
    public double calculatePremium(@RequestBody Policy policy) {
        return service.calculateTotalPremium(policy);
    }

}