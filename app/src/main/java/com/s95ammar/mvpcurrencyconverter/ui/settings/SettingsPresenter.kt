package com.s95ammar.mvpcurrencyconverter.ui.settings

import com.s95ammar.mvpcurrencyconverter.model.IRepository
import com.s95ammar.mvpcurrencyconverter.model.mappers.RatesMapper
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter

class SettingsPresenter(
    repository: IRepository,
    homeCountryCode: String,
    baseCurrencyCode: String,
    homeCurrencyCode: String?
) : BasePresenter<SettingsContract.View>(repository, homeCountryCode, baseCurrencyCode, homeCurrencyCode),
    SettingsContract.Presenter {

    override fun onAttach() {
        view?.showLoading()
        loadCurrenciesList()
    }

    private fun loadCurrenciesList() {
        singleHomeCurrencyCode
            .flatMap { repository.getRate(it) }
            .subIoObserveMain(
                onSuccess = { resp ->
                    val currencies = RatesMapper(resp).toEntity()
                    if (currencies.isNotEmpty()) view?.setUpSpinners(currencies)
                },
                onError = { throwable -> parseError(throwable) },
                doFinally = { view?.hideLoading() }

            )
    }

}
