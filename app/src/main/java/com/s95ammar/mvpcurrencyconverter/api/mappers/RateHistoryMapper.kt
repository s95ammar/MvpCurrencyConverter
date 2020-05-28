package com.s95ammar.mvpcurrencyconverter.api.mappers

import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.ui.home.entity.RateHistoryEntity

class RateHistoryMapper(private val conversions: List<ConversionResponse>) {

    fun toEntity(): RateHistoryEntity? {
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

        return RateHistoryEntity(fromCode, fromName, toCode, toName, datesToRates)
    }
}
