package com.s95ammar.mvpcurrencyconverter.model

import io.reactivex.Single

interface IRepository {
    fun getCountryCurrency(countryCode: String): Single<CountryCurrencyResponse.Currency>
    fun getRate(from: String, to: String = "", amount: Double = 1.0): Single<ConversionResponse>
    fun getRateHistory(date: String, from: String, to: String, amount: Double = 1.0): Single<ConversionResponse>
}