package com.s95ammar.mvpcurrencyconverter.ui.viewentities

data class RateHistoryViewEntity(
    val fromCode: String,
    val fromName: String,
    val toCode: String,
    val toName: String,
    val datesToRates: Map<String, Double>
)