package com.s95ammar.mvpcurrencyconverter.ui.viewentities

data class RateViewEntity(
    val fromCode: String,
    val fromName: String,
    val toCode: String,
    val toName: String,
    val rate: Double,
    val Date: String
)