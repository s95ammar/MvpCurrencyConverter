package com.s95ammar.mvpcurrencyconverter.model

import com.google.gson.annotations.SerializedName

data class CountryCurrencyResponse(

    @SerializedName("currencies")
    val currencies: List<Currency>

) {
    data class Currency(

        @SerializedName("code")
        val code: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("symbol")
        val symbol: String
    )
}