package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.HISTORY_DAYS_COUNT
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
            .flatMap { homeCountryCurrencyCode ->
                val historySinglesArray = Array(HISTORY_DAYS_COUNT) { i ->
                    repository.getRateHistory(
                        LocalDate.now().minusDays(i).toString(),
                        baseCurrencyCode,
                        homeCountryCurrencyCode
                    )
                }

                return@flatMap Single.zipArray<ConversionResponse, List<Pair<Double?, String?>>>(
                    Function {
                        return@Function it.filterIsInstance<ConversionResponse>().map { response ->
                            response.rates?.get(homeCountryCurrencyCode)?.rate to response.updatedDate
                        }
                    }, *historySinglesArray
                )
            }
            .subIoObserveMain(
                onSuccess = { history ->
                    logcat(functionName = "onSuccess", msg = history.toString())
                },
                onError = { throwable ->
                    logcat(functionName = "onError", msg = throwable.localizedMessage)
                },
                doFinally = { view?.hideLoading() }
            )
            .disposeBy(disposables)

    }

}