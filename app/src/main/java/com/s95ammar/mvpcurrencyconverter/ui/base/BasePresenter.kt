package com.s95ammar.mvpcurrencyconverter.ui.base

import androidx.annotation.Nullable
import com.s95ammar.mvpcurrencyconverter.Errors
import com.s95ammar.mvpcurrencyconverter.logcat
import com.s95ammar.mvpcurrencyconverter.model.IRepository
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

abstract class BasePresenter<V : BaseContract.View>(
    protected val repository: IRepository,
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
                .doOnSuccess {
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

    protected fun parseError(throwable: Throwable) {
        logcat(functionName = "onError", msg = throwable.localizedMessage)
        val error =  when (throwable) {
            is ConnectException,
            is UnknownHostException-> Errors.CONNECTION_ERROR
            is HttpException -> {
                when (throwable.code()) {
                    404 -> Errors.COUNTRY_UNAVAILABLE_ERROR
                    500 -> Errors.INTERNAL_SERVER_ERROR
                    else -> Errors.UNKNOWN_ERROR
                }
            }
            else -> Errors.UNKNOWN_ERROR
        }
        view?.onError(error)
    }

    override fun unsubscribe() {
        disposables.clear()
    }

    override fun detach() {
        view = null
    }
}
