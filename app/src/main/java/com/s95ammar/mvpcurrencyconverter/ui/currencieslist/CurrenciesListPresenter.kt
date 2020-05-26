package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.model.Repository
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

    }

    fun loadRatesFromAllToBase() {
//        repository.getRate()
    }
}