package org.example.core.dao;

import org.example.core.domain.RiskType;
import org.example.core.utils.GroovyScriptResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class RiskBasePremiumDAO {

    private final GroovyScriptResolver scriptResolver;

    @Autowired
    public RiskBasePremiumDAO(GroovyScriptResolver scriptResolver) {
        this.scriptResolver = scriptResolver;
    }

    public BigDecimal getRiskBasePremium(RiskType risk) {
        int riskBasePremium;
        List<LinkedHashMap<String, Serializable>> riskBasePremiumData =
                (List<LinkedHashMap<String, Serializable>>) scriptResolver.runMethodFromBaseScript("getRiskBasePremiumData");
        riskBasePremium = (int) riskBasePremiumData.stream()
                .filter(map -> map.containsValue(risk.name()))
                .findFirst()
                .get()
                .get("PREMIUM");
        return new BigDecimal(riskBasePremium);
    }

}