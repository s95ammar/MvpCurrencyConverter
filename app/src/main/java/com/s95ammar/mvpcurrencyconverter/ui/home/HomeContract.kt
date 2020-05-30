package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.ui.base.BaseContract
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateHistoryViewEntity

class HomeContract {

    interface Presenter : BaseContract.Presenter<View> {
        fun onRefresh()
    }

    interface View : BaseContract.View {
        fun displayHistory(history: RateHistoryViewEntity)
    }
}