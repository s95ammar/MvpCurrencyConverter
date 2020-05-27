package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.api.mappers.RateMapper
import com.s95ammar.mvpcurrencyconverter.logcat
import com.s95ammar.mvpcurrencyconverter.model.Repository
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter

class CurrenciesListPresenter(
    repository: Repository,
    homeCountryCode: String,
    baseCurrencyCode: String,
    homeCurrencyCode: String?
) : BasePresenter<CurrenciesListContract.View>(repository, homeCountryCode, baseCurrencyCode, homeCurrencyCode),
    CurrenciesListContract.Presenter {

    override fun onAttach() {
        view?.showLoading()
        onRefresh()
    }

    override fun onRefresh() {
        loadRatesFromAllToBase()
    }

    private fun loadRatesFromAllToBase() {
        singleHomeCurrencyCode
            .flatMap { repository.getRate(it) }
            .subIoObserveMain(
                onSuccess = { resp ->
                    val rates = RateMapper(resp).toEntity()
                    logcat(functionName = "onSuccess", msg = rates.toString())
                },
                onError = { throwable ->
                    logcat(functionName = "onError", msg = throwable.localizedMessage)
                },
                doFinally = { view?.hideLoading() }

            )


    }
}