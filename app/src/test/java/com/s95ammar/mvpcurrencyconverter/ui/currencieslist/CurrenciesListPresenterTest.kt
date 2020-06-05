package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.RxJavaTestingUtil
import com.s95ammar.mvpcurrencyconverter.model.FakeRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class CurrenciesListPresenterTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() = RxJavaTestingUtil.setUpRxSchedulers()
    }

    private lateinit var currenciesListPresenter: CurrenciesListPresenter

    @Before
    fun setupPresenter() {
        val fakeRepository = FakeRepository()
        val homeCountryCode = "UA"
        val baseCurrencyCode = "USD"
        val homeCurrencyCode: String? = null

        currenciesListPresenter = CurrenciesListPresenter(
            fakeRepository,
            homeCountryCode,
            baseCurrencyCode,
            homeCurrencyCode
        )
    }

    @Test
    fun loadRatesFromAllToHome_homeCurrencyCodeIsNull_setsHomeCurrencyCodeAndLoadsRates() {
        currenciesListPresenter.rates = null
        currenciesListPresenter.homeCurrencyCode = null

        currenciesListPresenter.loadRatesFromAllToHome()

        assertNotNull(currenciesListPresenter.homeCurrencyCode)
        assertNotNull(currenciesListPresenter.rates)
    }

    @Test
    fun loadRatesFromAllToHome_homeCurrencyCodeIsNotNull_loadsRates() {
        currenciesListPresenter.rates = null
        currenciesListPresenter.homeCurrencyCode = "UAH"

        currenciesListPresenter.loadRatesFromAllToHome()

        assertNotNull(currenciesListPresenter.rates)
    }

}
