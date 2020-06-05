package com.s95ammar.mvpcurrencyconverter.ui.settings

import com.s95ammar.mvpcurrencyconverter.ui.base.BaseContract
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateViewEntity

class SettingsContract {

    interface Presenter : BaseContract.Presenter<View> {
    }

    interface View : BaseContract.View {
        fun setUpSpinners(values: List<RateViewEntity>)
    }
}