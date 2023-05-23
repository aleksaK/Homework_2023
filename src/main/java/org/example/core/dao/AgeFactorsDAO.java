package org.example.core.dao;

import org.example.core.domain.Bicycle;
import org.example.core.utils.GroovyScriptResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AgeFactorsDAO {

    private final GroovyScriptResolver scriptResolver;

    @Autowired
    public AgeFactorsDAO(GroovyScriptResolver scriptResolver) {
        this.scriptResolver = scriptResolver;
    }

    public LinkedHashMap<String, Serializable> getAgeFactors(Bicycle bicycle) {

        List<LinkedHashMap<String, Serializable>> ageFactorData =
                (List<LinkedHashMap<String, Serializable>>) scriptResolver.runMethodFromBaseScript("getAgeFactorData");

        List<LinkedHashMap<String, Serializable>> makeModelAgeCriteriaFactorData = ageFactorData.stream()
                .filter(map -> map.containsKey("MAKE") && map.containsKey("MODEL") && map.containsKey("VALUE_FROM") && map.containsKey("VALUE_TO"))
                .toList();

        List<LinkedHashMap<String, Serializable>> makeAgeCriteriaFactorData = ageFactorData.stream()
                .filter(map -> map.containsKey("MAKE") && !map.containsKey("MODEL") && map.containsKey("VALUE_FROM") && map.containsKey("VALUE_TO"))
                .toList();

        List<LinkedHashMap<String, Serializable>> ageCriteriaFactorData = ageFactorData.stream()
                .filter(map -> !map.containsKey("MAKE") && !map.containsKey("MODEL") && map.containsKey("VALUE_FROM") && map.containsKey("VALUE_TO"))
                .toList();

        LinkedHashMap<String, Serializable> factors;

        factors = makeModelAgeCriteriaFactorData.stream()
                .filter(isMakeCriteriaMatched(bicycle).and(isModelCriteriaMatched(bicycle)).and(isAgeCriteriaMatched(bicycle)))
                .findFirst()
                .orElse(null);

        if (factors != null) {
            return factors;
        } else {
            factors = makeAgeCriteriaFactorData.stream()
                    .filter(isMakeCriteriaMatched(bicycle).and(isAgeCriteriaMatched(bicycle)))
                    .findFirst()
                    .orElse(null);
        }

        if (factors != null) {
            return factors;
        } else {
            factors = ageCriteriaFactorData.stream()
                    .filter(isAgeCriteriaMatched(bicycle))
                    .findFirst()
                    .orElse(null);
        }
        return factors;
    }

    private Predicate<LinkedHashMap<String, Serializable>> isMakeCriteriaMatched(Bicycle bicycle) {
        return map -> getEntryValueOrEmpty(map, "MAKE").equals(bicycle.getMake());
    }

    private Predicate<LinkedHashMap<String, Serializable>> isModelCriteriaMatched(Bicycle bicycle) {
        return map -> getEntryValueOrEmpty(map, "MODEL").equals(bicycle.getModel());
    }

    private Predicate<LinkedHashMap<String, Serializable>> isAgeCriteriaMatched(Bicycle bicycle) {
        int bicycleAge = LocalDateTime.now().getYear() - bicycle.getManufactureYear();
        return map -> Integer.parseInt(map.get("VALUE_FROM").toString()) <= bicycleAge
                && Integer.parseInt(map.get("VALUE_TO").toString()) >= bicycleAge;
    }

    private String getEntryValueOrEmpty(LinkedHashMap<String, Serializable> map, String entryKey) {
        String value = (String) map.get(entryKey);
        return value != null ? value : "";
    }

}