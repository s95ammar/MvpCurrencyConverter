package com.s95ammar.mvpcurrencyconverter.api

import com.s95ammar.mvpcurrencyconverter.BuildConfig
import com.s95ammar.mvpcurrencyconverter.Header
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        const val BASE_URL = "https://currency-converter5.p.rapidapi.com/currency/"
        val HEADER_HOST = Header("x-rapidapi-host", "currency-converter5.p.rapidapi.com")
        val HEADER_KEY =
            Header("x-rapidapi-key", "5463a50e64msha8b0f9b33d8f4edp121681jsn076c5988a5d9")
    }

    private val okHttpClient
        get() = OkHttpClient.Builder()
            .addLoggingInterceptorIfDebug()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val authorisedRequest: Request = chain.request().newBuilder()
                    .addHeader(HEADER_HOST.name, HEADER_HOST.value)
                    .addHeader(HEADER_KEY.name, HEADER_KEY.value)
                    .build()
                chain.proceed(authorisedRequest);
            }
            .build()

    private fun OkHttpClient.Builder.addLoggingInterceptorIfDebug() = apply {
        if (BuildConfig.DEBUG) addInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        )
    }

    val client: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}
