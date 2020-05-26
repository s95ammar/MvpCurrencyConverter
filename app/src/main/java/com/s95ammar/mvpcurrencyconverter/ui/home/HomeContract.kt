package com.s95ammar.mvpcurrencyconverter.ui.home

import androidx.annotation.Nullable
import com.s95ammar.mvpcurrencyconverter.ui.base.BaseContract
import org.jetbrains.annotations.NotNull

class HomeContract {

    interface Presenter : BaseContract.Presenter<View> {
    }

    interface View : BaseContract.View {
        fun setHomeCountryCurrencyCode(code: String)
    }
}