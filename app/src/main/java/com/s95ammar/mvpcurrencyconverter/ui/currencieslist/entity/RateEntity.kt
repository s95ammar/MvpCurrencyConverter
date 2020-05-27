package com.s95ammar.mvpcurrencyconverter.ui.currencieslist.entity

data class RateEntity(
    val fromCode: String,
    val fromName: String,
    val toCode: String,
    val toName: String,
    val rate: Double,
    val Date: String
)