package com.s95ammar.mvpcurrencyconverter.ui.base

import androidx.annotation.Nullable
import com.s95ammar.mvpcurrencyconverter.model.Repository
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.annotations.NotNull

abstract class BasePresenter<V : BaseContract.View>(
    protected val repository: Repository,
    private val homeCountryCode: String,
    protected val baseCurrencyCode: String,
    @Nullable private var homeCurrencyCode: String?
) : BaseContract.Presenter<V> {

    var view: V? = null
    protected var disposables: CompositeDisposable = CompositeDisposable()

    protected val singleHomeCurrencyCode
        get() = if (homeCurrencyCode == null) {
            repository.getCountryCurrency(homeCountryCode)
                .map { currency -> currency.code }
                .doAfterSuccess {
                    Single.just(it).subIoObserveMain {
                        view?.setHomeCountryCurrencyCode(it)
                        homeCurrencyCode = it
                    }
                }
        } else {
            Single.just(homeCurrencyCode)
        }

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
