package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.ui.base.BaseContract
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateViewEntity

class CurrenciesListContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun onRefresh()
    }

    interface View : BaseContract.View {
        fun setFromCode(value: String)
        fun setDate(value: String)
        fun displayRates(rates: List<RateViewEntity>)
    }
}