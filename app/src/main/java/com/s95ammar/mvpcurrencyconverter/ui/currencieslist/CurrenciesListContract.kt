package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.ui.base.BaseContract
import com.s95ammar.mvpcurrencyconverter.ui.currencieslist.entity.RateEntity

class CurrenciesListContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun onRefresh()
    }

    interface View : BaseContract.View {
        fun displayRates(rates: List<RateEntity>)
    }
}