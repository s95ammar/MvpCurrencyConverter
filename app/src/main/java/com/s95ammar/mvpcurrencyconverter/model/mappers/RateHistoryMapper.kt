package com.s95ammar.mvpcurrencyconverter.model.mappers

import com.s95ammar.mvpcurrencyconverter.model.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateHistoryViewEntity

class RateHistoryMapper(private val conversions: List<ConversionResponse>) {

    fun toEntity(): RateHistoryViewEntity? {
        val fromCode = conversions.first().baseCurrencyCode ?: return null
        val fromName = conversions.first().baseCurrencyName ?: return null
        val toCode = conversions.first().rates?.keys?.first() ?: return null
        val toName = conversions.first().rates?.values?.first()?.currencyName ?: return null

        val datesToRates = mutableMapOf<String, Double>()
        for (conversion in conversions) {
            val date = conversion.updatedDate ?: return null
            val rate = conversion.rates?.get(toCode)?.rate ?: return null
            datesToRates[date] = rate
        }

        return RateHistoryViewEntity(
            fromCode,
            fromName,
            toCode,
            toName,
            datesToRates
        )
    }
}
