package org.example.core.scripts

import org.example.core.dto.PremiumCalculationData

def calculatePremium(PremiumCalculationData data) {
    Script factors = getBinding().getVariable("factors") as Script
    def riskBasePremium = data.riskBasePremium as Double
    def sumInsuredFactor = factors.invokeMethod('calculateSumInsuredFactor', data) as Double
    def ageFactor = factors.invokeMethod('calculateBicycleAgeFactor', data) as Double
    return riskBasePremium * sumInsuredFactor * ageFactor
}