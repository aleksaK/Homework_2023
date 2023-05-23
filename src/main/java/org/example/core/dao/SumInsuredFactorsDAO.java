package org.example.core.dao;

import org.example.core.utils.GroovyScriptResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

@Component
public class SumInsuredFactorsDAO {

    private final GroovyScriptResolver scriptResolver;

    @Autowired
    public SumInsuredFactorsDAO(GroovyScriptResolver scriptResolver) {
        this.scriptResolver = scriptResolver;
    }

    public LinkedHashMap<String, Serializable> getSumInsuredFactors(BigDecimal objectSumInsured) {
        int objectSumInsuredInt = objectSumInsured.intValue();
        List<LinkedHashMap<String, Serializable>> sumInsuredFactorData =
                (List<LinkedHashMap<String, Serializable>>) scriptResolver.runMethodFromBaseScript("getSumInsuredFactorData");
        return sumInsuredFactorData.stream()
                .filter(isInRange(objectSumInsuredInt))
                .findFirst()
                .orElse(null);
    }

    private Predicate<LinkedHashMap<String, Serializable>> isInRange(int objectSumInsured) {
        return map -> Integer.parseInt(map.get("VALUE_FROM").toString()) <= objectSumInsured
                && Integer.parseInt(map.get("VALUE_TO").toString()) >= objectSumInsured;
    }

}