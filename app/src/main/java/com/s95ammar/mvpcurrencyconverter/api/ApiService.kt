package com.s95ammar.mvpcurrencyconverter.api

import com.s95ammar.mvpcurrencyconverter.model.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.model.CountryCurrencyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {
    @GET
    fun getCountryCurrency(@Url url: String): Single<CountryCurrencyResponse>

    @GET("convert")
    fun getRate(
        @Query("from") from: String,
        @Query("to") to: String = "",
        @Query("amount") amount: Double = 1.0
    ): Single<ConversionResponse>

    @GET("convert")
    fun getRatesToAll(
        @Query("from") from: String
    ): Single<ConversionResponse>

    @GET("historical/{date}")
    fun getRateHistory(
        @Path("date") date: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double = 1.0
    ): Single<ConversionResponse>
}
