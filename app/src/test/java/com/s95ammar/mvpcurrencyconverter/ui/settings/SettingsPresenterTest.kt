package com.s95ammar.mvpcurrencyconverter.ui.settings

import com.s95ammar.mvpcurrencyconverter.RxJavaTestingUtil
import com.s95ammar.mvpcurrencyconverter.model.FakeRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class SettingsPresenterTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() = RxJavaTestingUtil.setUpRxSchedulers()
    }

    private lateinit var settingsPresenter: SettingsPresenter

    @Before
    fun setupPresenter() {
        val fakeRepository = FakeRepository()
        val homeCountryCode = "UA"
        val baseCurrencyCode = "USD"
        val homeCurrencyCode: String? = null

        settingsPresenter = SettingsPresenter(
            fakeRepository,
            homeCountryCode,
            baseCurrencyCode,
            homeCurrencyCode
        )
    }

    @Test
    fun loadCurrenciesList_homeCurrencyCodeIsNull_setsHomeCurrencyCodeAndLoadsCurrenciesList() {
        settingsPresenter.homeCurrencyCode = null
        settingsPresenter.currencies = null

        settingsPresenter.loadCurrenciesList()

        assertNotNull(settingsPresenter.homeCurrencyCode)
        assertNotNull(settingsPresenter.currencies)
    }

    @Test
    fun loadCurrenciesList_homeCurrencyCodeIsNotNull_loadsCurrenciesList() {
        settingsPresenter.currencies = null
        settingsPresenter.homeCurrencyCode = "UAH"

        settingsPresenter.loadCurrenciesList()

        assertNotNull(settingsPresenter.currencies)
    }

}
