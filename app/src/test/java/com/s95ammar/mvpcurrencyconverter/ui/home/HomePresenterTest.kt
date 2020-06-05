package com.s95ammar.mvpcurrencyconverter.ui.home

import com.s95ammar.mvpcurrencyconverter.RxJavaTestingUtil
import com.s95ammar.mvpcurrencyconverter.model.FakeRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test


class HomePresenterTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() = RxJavaTestingUtil.setUpRxSchedulers()
    }

    private lateinit var homePresenter: HomePresenter

    @Before
    fun setupPresenter() {
        val fakeRepository = FakeRepository()
        val homeCountryCode = "UA"
        val baseCurrencyCode = "USD"
        val homeCurrencyCode: String? = null

        homePresenter = HomePresenter(
            fakeRepository,
            homeCountryCode,
            baseCurrencyCode,
            homeCurrencyCode
        )
    }

    @Test
    fun loadBaseToHomeHistory_homeCurrencyCodeIsNull_setsHomeCurrencyCodeAndLoadsHistory() {
        homePresenter.history = null
        homePresenter.homeCurrencyCode = null

        homePresenter.loadBaseToHomeHistory()

        assertNotNull(homePresenter.homeCurrencyCode)
        assertNotNull(homePresenter.history)
    }

    @Test
    fun loadBaseToHomeHistory_homeCurrencyCodeIsNotNull_loadsHistory() {
        homePresenter.history = null
        homePresenter.homeCurrencyCode = "UAH"

        homePresenter.loadBaseToHomeHistory()

        assertNotNull(homePresenter.history)
    }

}
