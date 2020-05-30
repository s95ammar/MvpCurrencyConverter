package com.s95ammar.mvpcurrencyconverter.model

import com.s95ammar.mvpcurrencyconverter.api.ApiService
import com.s95ammar.mvpcurrencyconverter.api.RetrofitClient
import io.reactivex.Single

class Repository(private val apiService: ApiService) {

    fun getCountryCurrency(countryCode: String): Single<CountryCurrencyResponse.Currency> {
        return apiService.getCountryCurrency("${RetrofitClient.COUNTRY_CURRENCY_URL}$countryCode")
            .map { currenciesResp -> currenciesResp.currencies.firstOrNull() }
    }

    fun getRate(from: String, to: String = "", amount: Double = 1.0): Single<ConversionResponse> {
        return apiService.getRate(from, to, amount)
    }

    fun getRateHistory(date: String, from: String, to: String, amount: Double = 1.0): Single<ConversionResponse> {
        return apiService.getRateHistory(date, from, to, amount)
    }

}