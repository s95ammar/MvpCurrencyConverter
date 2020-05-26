package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.model.Repository
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter

class CurrenciesListPresenter(repository: Repository) :
    BasePresenter<CurrenciesListContract.View>(repository), CurrenciesListContract.Presenter {

    override fun onRefresh() {

    }
}