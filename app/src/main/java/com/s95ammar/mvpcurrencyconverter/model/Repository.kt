package com.s95ammar.mvpcurrencyconverter.model

import com.s95ammar.mvpcurrencyconverter.api.ApiService
import com.s95ammar.mvpcurrencyconverter.api.RetrofitClient
import io.reactivex.Single

class Repository(private val apiService: ApiService) : IRepository {

    override fun getCountryCurrency(countryCode: String): Single<CountryCurrencyResponse.Currency> {
        return apiService.getCountryCurrency("${RetrofitClient.COUNTRY_CURRENCY_URL}$countryCode")
            .map { currenciesResp -> currenciesResp.currencies.firstOrNull() }
    }

    override fun getRates(from: String): Single<ConversionResponse> {
        return apiService.getRate(from)
    }

    override fun getRateHistory(date: String, from: String, to: String): Single<ConversionResponse> {
        return apiService.getRateHistory(date, from, to)
    }

}