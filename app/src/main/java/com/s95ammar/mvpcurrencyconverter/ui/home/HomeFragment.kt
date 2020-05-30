package com.s95ammar.mvpcurrencyconverter.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.s95ammar.mvpcurrencyconverter.R
import com.s95ammar.mvpcurrencyconverter.ServiceLocator
import com.s95ammar.mvpcurrencyconverter.ui.activity.LoadingManager
import com.s95ammar.mvpcurrencyconverter.ui.base.BaseFragment
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateHistoryViewEntity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeContract.Presenter>(), HomeContract.View {

    override fun providePresenter() = HomePresenter(
        ServiceLocator.repository,
        application.homeCountryCode,
        application.baseCurrencyCode,
        application.homeCurrencyCode
    )

    private var loadingManager: LoadingManager? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadingManager = context as? LoadingManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        home_layout_swipe_to_refresh.setOnRefreshListener { presenter.onRefresh() }
    }

    override fun showLoading() {
        loadingManager?.showLoading()
    }

    override fun hideLoading() {
        loadingManager?.hideLoading()
        home_layout_swipe_to_refresh.isRefreshing = false
    }

    override fun displayHistory(history: RateHistoryViewEntity) {
        chart.visibility = View.VISIBLE
        val xValues = history.datesToRates.values
        val yValues = history.datesToRates.toList().mapIndexed { i, pair ->  Entry(i.toFloat(), pair.second.toFloat()) }
        val lineDataSet1 = LineDataSet(yValues, "test")
        val dataSets = mutableListOf<ILineDataSet>().apply { add(lineDataSet1) }
        chart.animate()
        chart.data = LineData(dataSets)
    }
}
