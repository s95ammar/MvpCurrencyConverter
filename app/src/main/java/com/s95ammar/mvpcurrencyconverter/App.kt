package com.s95ammar.mvpcurrencyconverter

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager


class App: Application() {

    companion object {
        const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES"
    }

    var homeCountryCurrencyCode: String? = null

    fun getCountryCode(): String {
        return (applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkCountryIso
    }


}