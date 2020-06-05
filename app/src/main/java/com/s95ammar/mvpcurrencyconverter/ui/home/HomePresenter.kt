package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.Constants
import com.s95ammar.mvpcurrencyconverter.Errors
import com.s95ammar.mvpcurrencyconverter.disposeBy
import com.s95ammar.mvpcurrencyconverter.model.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.model.IRepository
import com.s95ammar.mvpcurrencyconverter.model.mappers.RateHistoryMapper
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateHistoryViewEntity
import io.reactivex.Single
import io.reactivex.functions.Function
import org.joda.time.LocalDate

class HomePresenter(
    repository: IRepository,
    homeCountryCode: String,
    baseCurrencyCode: String,
    homeCurrencyCode: String?
) : BasePresenter<HomeContract.View>(repository, homeCountryCode, baseCurrencyCode, homeCurrencyCode),
    HomeContract.Presenter {

    var history: RateHistoryViewEntity? = null

    override fun onAttach() {
        view?.showLoading()
        onRefresh()
    }

    override fun onRefresh() {
        loadBaseToHomeHistory()
    }

    fun loadBaseToHomeHistory() {
        singleHomeCurrencyCode
            .flatMap { homeCurrencyCode ->

                val historySinglesArray = Array(Constants.HISTORY_DAYS_COUNT) { i ->
                    repository.getRateHistory(
                        LocalDate.now().minusDays(Constants.HISTORY_DAYS_COUNT - i - 1).toString(),
                        baseCurrencyCode,
                        homeCurrencyCode
                    )
                }

                return@flatMap Single.zipArray<ConversionResponse, List<ConversionResponse>>(
                    Function { arr -> arr.filterIsInstance<ConversionResponse>() },
                    *historySinglesArray
                )

            }
            .subIoObserveMain(
                onSuccess = { response ->
                    val history = RateHistoryMapper(response).toEntity()
                    this.history = history
                    if (history != null) {
                        view?.setCurrencyCodes(history.fromCode, history.toCode)
                        view?.setDateRange(history.datesToRates.keys.first(), history.datesToRates.keys.last())
                        view?.displayHistory(history)
                    } else {
                        view?.onError(Errors.COUNTRY_UNAVAILABLE_ERROR)
                    }
                },
                onError = { throwable -> parseError(throwable) },
                doFinally = { view?.hideLoading() }
            )
            .disposeBy(disposables)

    }

}