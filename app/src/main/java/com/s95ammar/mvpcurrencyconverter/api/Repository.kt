package com.s95ammar.mvpcurrencyconverter.api

import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import io.reactivex.Single

class Repository(private val apiService: ApiService) {

    fun getRate(from: String, to: String, amount: Double): Single<ConversionResponse> {
        return apiService.getRate(from, to, amount)
    }

    fun getRateHistory(date: String, from: String, to: String, amount: Double = 1.0): Single<ConversionResponse> {
        return apiService.getRateHistory(date, from, to, amount)
    }

}