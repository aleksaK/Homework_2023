package org.example.core.scripts

import org.example.core.dto.PremiumCalculationData

def calculateSumInsuredFactor(PremiumCalculationData data) {
    def factorMin = data.getSumInsuredFactors().get('FACTOR_MIN') as Double
    def factorMax = data.getSumInsuredFactors().get('FACTOR_MAX') as Double
    def sumInsuredMin = data.getSumInsuredFactors().get('VALUE_FROM') as Double
    def sumInsuredMax = data.getSumInsuredFactors().get('VALUE_TO') as Double
    def riskSumInsured = data.getObjectSumInsured() as Double
    return factorMax - (factorMax - factorMin) * (sumInsuredMax - riskSumInsured) / (sumInsuredMax - sumInsuredMin)
}

def calculateBicycleAgeFactor(PremiumCalculationData data) {
    def factorMin = data.getAgeFactors().get('FACTOR_MIN') as Double
    def factorMax = data.getAgeFactors().get('FACTOR_MAX') as Double
    def ageMin = data.getAgeFactors().get('VALUE_FROM') as Double
    def ageMax = data.getAgeFactors().get('VALUE_TO') as Double
    def ageActual = data.getAgeActual() as Double
    return factorMax - (factorMax - factorMin) * (ageMax - ageActual) / (ageMax - ageMin)
}