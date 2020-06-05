package com.s95ammar.mvpcurrencyconverter.model

import io.reactivex.Single

interface IRepository {
    fun getCountryCurrency(countryCode: String): Single<CountryCurrencyResponse.Currency>
    fun getRates(from: String): Single<ConversionResponse>
    fun getRateHistory(date: String, from: String, to: String): Single<ConversionResponse>
}