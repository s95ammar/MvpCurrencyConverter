package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.disposeBy
import com.s95ammar.mvpcurrencyconverter.logcat
import com.s95ammar.mvpcurrencyconverter.model.Repository
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class HomePresenter(repository: Repository) : BasePresenter<HomeContract.View>(repository), HomeContract.Presenter {

    override fun getCountryCurrency(countryCode: String) {
        view?.showLoading()
        repository.getCountryCurrency(countryCode)
            .delay(1, TimeUnit.SECONDS) // just for demonstration purposes
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { view?.hideLoading() }
            .subscribe(
                { currency ->
                    view?.setHomeCountryCurrencyCode(currency.code)
                    logcat(functionName = "onSuccess", msg = currency.toString())
                },
                { throwable ->
                    logcat(functionName = "onError", msg = throwable.localizedMessage)
                }
            )
            .disposeBy(disposables)

    }

    fun getRateHistory(from: String, to: String, date: String) {
        view?.showLoading()
        repository.getRateHistory(from, to, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { view?.hideLoading() }
            .subscribe(
                { conversionResp ->
                    logcat(functionName = "onSuccess", msg = conversionResp.toString())
                },
                { throwable ->
                    logcat(functionName = "onError", msg = throwable.localizedMessage)
                }
            )
            .disposeBy(disposables)
    }

}