package com.s95ammar.mvpcurrencyconverter.api.mappers

import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.model.entity.RatesEntity

class RateMapper(private val conversion: ConversionResponse) {

    fun toEntity(): RatesEntity? {
        val toCode= conversion.baseCurrencyCode ?: return null

        val rates = mutableMapOf<String, Double>()
        conversion.rates?.forEach { (fromCode, targetCurrency) ->
            targetCurrency.rate?.let { rate -> rates[fromCode] = 1 / rate }
        }

        return RatesEntity(toCode, rates, conversion.updatedDate)
    }
}
