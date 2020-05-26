package com.s95ammar.mvpcurrencyconverter.ui.base

import com.s95ammar.mvpcurrencyconverter.model.Repository
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View>(val repository: Repository)
    : BaseContract.Presenter<V> {

    var view: V? = null
    protected var disposables: CompositeDisposable = CompositeDisposable()

    override fun attach(view: V) {
        this.view = view
        onAttach()
    }

    protected open fun onAttach() {}

    override fun unsubscribe() {
        disposables.dispose()
    }

    override fun detach() {
        view = null
    }
}
