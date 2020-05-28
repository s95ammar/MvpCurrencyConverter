package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.HISTORY_DAYS_COUNT
import com.s95ammar.mvpcurrencyconverter.api.mappers.RateHistoryMapper
import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.disposeBy
import com.s95ammar.mvpcurrencyconverter.logcat
import com.s95ammar.mvpcurrencyconverter.model.Repository
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter
import io.reactivex.Single
import io.reactivex.functions.Function
import org.joda.time.LocalDate

class HomePresenter(
    repository: Repository,
    homeCountryCode: String,
    baseCurrencyCode: String,
    homeCurrencyCode: String?
) : BasePresenter<HomeContract.View>(repository, homeCountryCode, baseCurrencyCode, homeCurrencyCode),
    HomeContract.Presenter {

    override fun onAttach() {
        view?.showLoading()
        onRefresh()
    }

    override fun onRefresh() {
        loadBaseToHomeHistory()
    }

    private fun loadBaseToHomeHistory() {
        singleHomeCurrencyCode
            .flatMap { homeCurrencyCode ->

                val historySinglesArray = Array(HISTORY_DAYS_COUNT) { i ->
                    repository.getRateHistory(LocalDate.now().minusDays(HISTORY_DAYS_COUNT - i - 1).toString(), baseCurrencyCode, homeCurrencyCode)
                }

                return@flatMap Single.zipArray<ConversionResponse, List<ConversionResponse>>(
                    Function { arr -> arr.filterIsInstance<ConversionResponse>() },
                    *historySinglesArray
                )

            }
            .subIoObserveMain(
                onSuccess = { response ->
                    RateHistoryMapper(response).toEntity()?.let { history ->
                        view?.displayHistory(history)
                    }
                },
                onError = { throwable ->
                    logcat(functionName = "onError", msg = throwable.localizedMessage)
                },
                doFinally = { view?.hideLoading() }
            )
            .disposeBy(disposables)

    }

}