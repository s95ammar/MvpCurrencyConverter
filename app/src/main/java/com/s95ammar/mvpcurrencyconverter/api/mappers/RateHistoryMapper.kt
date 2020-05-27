package com.s95ammar.mvpcurrencyconverter.api.mappers

import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.ui.home.entity.RateHistoryEntity

class RateHistoryMapper(private val conversions: List<ConversionResponse>) {

    fun toEntity(): RateHistoryEntity? {
        val sourceCurrency = conversions.first().baseCurrencyCode ?: return null
        val targetCurrency = conversions.first().rates?.keys?.first() ?: return null

        val datesToRates = mutableMapOf<String, Double>()
        for (conversion in conversions) {
            val date = conversion.updatedDate ?: return null
            val rate = conversion.rates?.get(targetCurrency)?.rate ?: return null
            datesToRates[date] = rate
        }

        return RateHistoryEntity(
            sourceCurrency,
            targetCurrency,
            datesToRates
        )
    }
}
