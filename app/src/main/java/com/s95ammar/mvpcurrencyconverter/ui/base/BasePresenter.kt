package com.s95ammar.mvpcurrencyconverter.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View>
    : BaseContract.Presenter<V> {

    var view: V? = null
    private var disposables: CompositeDisposable? = null

    override fun attach(view: V) {
        this.view = view
    }

    override fun unsubscribe() {
//        TODO: clear disposables
    }

    override fun detach() {
        view = null
    }
}
