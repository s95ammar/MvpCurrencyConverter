package com.s95ammar.mvpcurrencyconverter.ui.home.entity

data class RateHistoryEntity(val fromCode: String, val toCode: String, val datesToRates: Map<String, Double>)