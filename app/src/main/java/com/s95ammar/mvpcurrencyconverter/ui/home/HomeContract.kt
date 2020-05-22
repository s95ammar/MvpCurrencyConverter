package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.ui.base.BaseContract

interface HomeContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun getCountryCurrency(countryCode: String)
    }

    interface View : BaseContract.View {
        fun setHomeCountryCurrencyCode(code: String)
    }
}