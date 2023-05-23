package org.example.core.dao;

import org.example.core.utils.GroovyScriptResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RiskCountFactorsDAO {

    private final GroovyScriptResolver scriptResolver;

    @Autowired
    public RiskCountFactorsDAO(GroovyScriptResolver scriptResolver) {
        this.scriptResolver = scriptResolver;
    }

    public LinkedHashMap<String, Serializable> getRiskCountFactors(int riskCount) {
        List<LinkedHashMap<String, Serializable>> riskCountFactorData =
                (List<LinkedHashMap<String, Serializable>>) scriptResolver.runMethodFromBaseScript("getRiskCountFactorData");
        return riskCountFactorData.stream()
                .filter(isInRange(riskCount))
                .findFirst()
                .orElse(null);
    }

    private Predicate<LinkedHashMap<String, Serializable>> isInRange(int objectSumInsured) {
        return map -> Integer.parseInt(map.get("VALUE_FROM").toString()) <= objectSumInsured
                && Integer.parseInt(map.get("VALUE_TO").toString()) >= objectSumInsured;
    }

}