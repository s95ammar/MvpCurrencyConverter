package com.s95ammar.mvpcurrencyconverter.model.entity

data class RatesEntity(val toCode: String, val fromCodesToRates: Map<String, Double>, val Date: String? = null)