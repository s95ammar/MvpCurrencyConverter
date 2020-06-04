package com.s95ammar.mvpcurrencyconverter.model

import io.reactivex.Single
import org.junit.Test

import org.junit.Assert.*

class FakeRepository: IRepository {
    override fun getCountryCurrency(countryCode: String): Single<CountryCurrencyResponse.Currency> {
        TODO("Not yet implemented")
    }

    override fun getRate(from: String, to: String, amount: Double): Single<ConversionResponse> {
        TODO("Not yet implemented")
    }

    override fun getRateHistory(date: String, from: String, to: String, amount: Double): Single<ConversionResponse> {
        TODO("Not yet implemented")
    }

}