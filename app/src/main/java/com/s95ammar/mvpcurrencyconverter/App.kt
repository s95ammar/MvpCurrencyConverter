package com.s95ammar.mvpcurrencyconverter

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager

class App: Application() {

    var homeCurrencyCode: String? = null
    var baseCurrencyCode = Constants.USD
    val homeCountryCode: String
        get() = (applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).networkCountryIso

}