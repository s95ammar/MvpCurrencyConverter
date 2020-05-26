package com.s95ammar.mvpcurrencyconverter.ui.home

import androidx.annotation.Nullable
import com.s95ammar.mvpcurrencyconverter.HISTORY_DAYS_COUNT
import com.s95ammar.mvpcurrencyconverter.api.resp.ConversionResponse
import com.s95ammar.mvpcurrencyconverter.disposeBy
import com.s95ammar.mvpcurrencyconverter.logcat
import com.s95ammar.mvpcurrencyconverter.model.Repository
import com.s95ammar.mvpcurrencyconverter.subIoObserveMain
import com.s95ammar.mvpcurrencyconverter.ui.base.BasePresenter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.NotNull
import org.joda.time.LocalDate

class HomePresenter(
    repository: Repository,
    @NotNull private val homeCountryCode: String,
    @NotNull private val baseCurrencyCode: String,
    @Nullable private var homeCurrencyCode: String?
) : BasePresenter<HomeContract.View>(repository), HomeContract.Presenter {

    override fun onAttach() {
        view?.showLoading()
        onRefresh()
    }

    override fun onRefresh() {
        loadBaseToHomeHistory()
    }

    private val singleHomeCurrencyCode
        get() = if (homeCurrencyCode == null) {
            repository.getCountryCurrency(homeCountryCode)
                .map { currency -> currency.code }
                .doAfterSuccess {
                    Single.just(it).subIoObserveMain {
                        view?.setHomeCountryCurrencyCode(it)
                        homeCurrencyCode = it
                    }
                }
        } else {
            Single.just(homeCurrencyCode)
        }

    private fun loadBaseToHomeHistory() {
        singleHomeCurrencyCode
            .flatMap { homeCountryCurrencyCode ->
                val historySinglesArray = Array(HISTORY_DAYS_COUNT) { i ->
                    repository.getRateHistory(LocalDate.now().minusDays(i).toString(), baseCurrencyCode, homeCountryCurrencyCode)
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

    private fun getHistorySinglesArray(from: String, to: String): Array<Single<ConversionResponse>?> {
        return Array(HISTORY_DAYS_COUNT) { i ->
            repository.getRateHistory(LocalDate.now().minusDays(i).toString(), from, to)
        }
    }

    fun getRateHistory(from: String, to: String, date: String) {
        view?.showLoading()
        repository.getRateHistory(from, to, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { view?.hideLoading() }
            .subscribe(
                { conversionResp ->
                    logcat(functionName = "onSuccess", msg = conversionResp.toString())
                },
                { throwable ->
                    logcat(functionName = "onError", msg = throwable.localizedMessage)
                }
            )
            .disposeBy(disposables)
    }

}