package com.s95ammar.mvpcurrencyconverter.model

import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*

class FakeRepository : IRepository {
    override fun getCountryCurrency(countryCode: String): Single<CountryCurrencyResponse.Currency> {
        return Single.just(CountryCurrencyResponse.Currency("UAH", "Ukrainian hryvnia", "₴"))
    }

    override fun getRate(from: String, to: String, amount: Double): Single<ConversionResponse> {
        TODO("Not yet implemented")
    }

    override fun getRateHistory(date: String, from: String, to: String, amount: Double): Single<ConversionResponse> {
        val response = ConversionResponse(
            amount = 1.0,
            baseCurrencyCode = "USD",
            baseCurrencyName = "US Dollar",
            rates = mapOf("UAH" to ConversionResponse.TargetCurrency("Ukrainian Hryvnia", 26.9842, 26.9842)),
            updatedDate = date
        )
        return Single.just(response)
    }

}