package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import com.s95ammar.mvpcurrencyconverter.RxJavaTestingUtil
import com.s95ammar.mvpcurrencyconverter.model.FakeRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import kotlin.NumberFormatException

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
        currenciesListPresenter.homeCurrencyCode = null
        currenciesListPresenter.rates = null

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

    @Test
    fun onUserInput_inputIsPositiveValue_doesNotThrowException() {
        currenciesListPresenter.onUserInput("5")
    }

    @Test
    fun onUserInput_inputIsNegativeValue_doesNotThrowException() {
        currenciesListPresenter.onUserInput("-5")
    }

    @Test
    fun onUserInput_inputIsDoubleValue_doesNotThrowException() {
        currenciesListPresenter.onUserInput("1/2")
    }

    @Test(expected = NumberFormatException::class)
    fun onUserInput_inputIsNotANumber_doesNotThrowException() {
        currenciesListPresenter.onUserInput("a")
    }

}
