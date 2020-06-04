package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.Errors
import com.s95ammar.mvpcurrencyconverter.model.IRepository
import com.s95ammar.mvpcurrencyconverter.model.mappers.RatesMapper
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter

class CurrenciesListPresenter(
    repository: IRepository,
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
                    val rates = RatesMapper(resp).toEntity()
                    if (rates.isNotEmpty()) {
                        view?.setFromCode(rates.first().toCode)
                        view?.setDate(rates.first().Date)
                        view?.displayRates(rates)
                    } else {
                        view?.onError(Errors.COUNTRY_UNAVAILABLE_ERROR)
                    }
                },
                onError = { throwable -> parseError(throwable) },
                doFinally = { view?.hideLoading() }

            )


    }
}