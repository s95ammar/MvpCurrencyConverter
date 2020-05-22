package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.disposeBy
import com.s95ammar.mvpcurrencyconverter.logcat
import com.s95ammar.mvpcurrencyconverter.model.Repository
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(repository: Repository) : BasePresenter<HomeContract.View>(repository), HomeContract.Presenter {

    override fun getCountryCurrency(countryCode: String) {
//        TODO: showLoading
        repository.getCountryCurrency(countryCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { /*TODO: hideLoading*/ }
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

}