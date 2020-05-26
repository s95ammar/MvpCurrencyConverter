package com.s95ammar.mvpcurrencyconverter.ui.base

class BaseContract {

    interface Presenter<V : View>{

        fun attach(view: V)

        fun onRefresh()

        fun unsubscribe()

        fun detach()

    }

    interface View {

        fun setHomeCountryCurrencyCode(code: String)

        fun showToast(msg: String)

        fun showLoading() {}

        fun hideLoading() {}

    }

}
