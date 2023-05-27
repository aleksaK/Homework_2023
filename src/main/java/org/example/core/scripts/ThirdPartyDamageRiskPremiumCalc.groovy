package org.example.core.scripts

import org.example.core.dto.PremiumCalculationData
import org.example.core.utils.GroovyScriptResolver

def calculatePremium(PremiumCalculationData data) {
    Script factors = getBinding().getVariable("factors") as Script
    def riskBasePremium = data.riskBasePremium as Double
    def sumInsuredFactor = factors.invokeMethod('calculateSumInsuredFactor', data) as Double
    def riskCountFactor = data.riskCountFactors.get('FACTOR_MAX') as Double
    return riskBasePremium * sumInsuredFactor * riskCountFactor
}