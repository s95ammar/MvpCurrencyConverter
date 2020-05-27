package com.s95ammar.mvpcurrencyconverter.model.entity

data class RateHistoryEntity(val fromCode: String, val toCode: String, val datesToRates: Map<String, Double>)