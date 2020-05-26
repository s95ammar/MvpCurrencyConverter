package com.s95ammar.mvpcurrencyconverter

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager


class App: Application() {

    companion object {
        const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES"
    }

    var homeCurrencyCode: String? = null
    var baseCurrencyCode = USD
    val homeCountryCode: String
        get() = (applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkCountryIso


}