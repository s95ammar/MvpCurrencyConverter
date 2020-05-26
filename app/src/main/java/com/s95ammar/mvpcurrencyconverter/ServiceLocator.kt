package com.s95ammar.mvpcurrencyconverter

import com.s95ammar.mvpcurrencyconverter.api.ApiService
import com.s95ammar.mvpcurrencyconverter.api.RetrofitClient
import com.s95ammar.mvpcurrencyconverter.model.Repository

object ServiceLocator {
    val repository by lazy { Repository(createApiService()) }

    private fun createApiService(): ApiService {
        return RetrofitClient()
            .client
            .create(ApiService::class.java)
    }

}