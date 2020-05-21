package com.s95ammar.mvpcurrencyconverter.ui.base

class BaseContract {

    interface Presenter<V : View>{

        fun attach(view: V)

        fun unsubscribe()

        fun detach()

    }

    interface View {

        fun showToast(msg: String)

    }

}
