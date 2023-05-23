package org.example.core.scripts

import org.example.core.dto.PremiumCalculationData
import org.example.core.utils.GroovyScriptResolver

def calculatePremium(PremiumCalculationData data) {
    GroovyScriptResolver resolver = new GroovyScriptResolver(new GroovyShell())
    def riskBasePremium = data.riskBasePremium as Double
    def sumInsuredFactor = resolver.runMethodFromScript('factors.groovy', 'calculateSumInsuredFactor', data) as Double
    def ageFactor = resolver.runMethodFromScript('factors.groovy', 'calculateBicycleAgeFactor', data) as Double
    return riskBasePremium * sumInsuredFactor * ageFactor
}