package org.example.core.scripts

import org.example.core.dto.PremiumCalculationData
import org.example.core.utils.GroovyScriptResolver

def calculatePremium(PremiumCalculationData data) {
    GroovyScriptResolver resolver = new GroovyScriptResolver(new GroovyShell())
    def riskBasePremium = data.riskBasePremium as Double
    def sumInsuredFactor = resolver.runMethodFromScript('factors.groovy', 'calculateSumInsuredFactor', data) as Double
    def riskCountFactor = data.riskCountFactors.get('FACTOR_MAX') as Double
    return riskBasePremium * sumInsuredFactor * riskCountFactor
}