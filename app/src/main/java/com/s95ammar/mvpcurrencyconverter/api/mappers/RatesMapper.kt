package com.s95ammar.mvpcurrencyconverter.api.mappers

import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateViewEntity

class RatesMapper(private val conversion: ConversionResponse) {

    fun toEntity(): List<RateViewEntity> {
        val toCode= conversion.baseCurrencyCode ?: return emptyList()
        val toName= conversion.baseCurrencyName ?: return emptyList()

        val rates = mutableListOf<RateViewEntity>()
        conversion.rates?.forEach { (fromCode, targetCurrency) ->
            val inverseRate = targetCurrency.rate ?: return emptyList()
            val fromName = targetCurrency.currencyName ?: return emptyList()
            val date = conversion.updatedDate ?: return emptyList()

            rates.add(
                RateViewEntity(
                    fromCode,
                    fromName,
                    toCode,
                    toName,
                    1 / inverseRate,
                    date
                )
            )
        }

        return rates
    }
}
