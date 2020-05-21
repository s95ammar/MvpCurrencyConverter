package com.s95ammar.mvpcurrencyconverter.api

import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("convert")
    fun getRate(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
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
        @Query("amount") amount: Double
    ): Single<ConversionResponse>
}
