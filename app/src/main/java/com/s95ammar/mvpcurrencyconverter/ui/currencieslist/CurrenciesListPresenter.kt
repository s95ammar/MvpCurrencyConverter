package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.Errors
import com.s95ammar.mvpcurrencyconverter.model.IRepository
import com.s95ammar.mvpcurrencyconverter.model.mappers.RatesMapper
import com.s95ammar.mvpcurrencyconverter.parseFraction
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateViewEntity

class CurrenciesListPresenter(
    repository: IRepository,
    homeCountryCode: String,
    baseCurrencyCode: String,
    homeCurrencyCode: String?
) : BasePresenter<CurrenciesListContract.View>(repository, homeCountryCode, baseCurrencyCode, homeCurrencyCode),
    CurrenciesListContract.Presenter {

    var rates: List<RateViewEntity>? = null
    var ratesByInput: List<RateViewEntity>? = null

    override fun onAttach() {
        view?.showLoading()
        onRefresh()
    }

    override fun onRefresh() {
        loadRatesFromAllToHome()
    }

    fun loadRatesFromAllToHome() {
        singleHomeCurrencyCode
            .flatMap { repository.getRates(it) }
            .subIoObserveMain(
                onSuccess = { resp ->
                    val rates = RatesMapper(resp).toEntity()
                    this.rates = rates
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

    override fun onUserInput(input: String) {
        val inputValue = if (input.contains("/")) parseFraction(input) else input.toDouble()
        ratesByInput = rates?.map { it.copy(rate = it.rate * inputValue) }?.also {
            view?.displayRates(it)
        }

    }
}