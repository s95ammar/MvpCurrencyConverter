package com.s95ammar.mvpcurrencyconverter.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.s95ammar.mvpcurrencyconverter.*
import com.s95ammar.mvpcurrencyconverter.ui.activity.LoadingManager
import com.s95ammar.mvpcurrencyconverter.ui.base.BaseFragment
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateHistoryViewEntity
import kotlinx.android.synthetic.main.fragment_home.*
import org.joda.time.LocalDate


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

    override fun setCurrencyCodes(from: String, to: String) {
        history_rate_text_view.text = resources.getString(R.string.history_rate_of_by, from, to)
    }

    override fun setDateRange(from: String, to: String) {
        val fromFormatted = LocalDate(from).toString(Constants.DATE_PATTERN_DD_MM)
        val toFormatted = LocalDate(to).toString(Constants.DATE_PATTERN_DD_MM)
        history_date_range_text_view.text = resources.getString(R.string.history_date_range, fromFormatted, toFormatted)
    }

    override fun displayHistory(history: RateHistoryViewEntity) {
        val xValues = history.datesToRates.keys.map { LocalDate(it).toString(Constants.DATE_PATTERN_DD_MM) }

        chart.apply {
            visibility = View.VISIBLE
            xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setLabelCount(xValues.size, true)
            setScaleEnabled(false)
            axisRight.isEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
        }

        val yValues = history.datesToRates.values.mapIndexed { i, value -> Entry(i.toFloat(), value.toFloat()) }
        val lineDataSet = LineDataSet(yValues, "").apply {
            lineWidth = Constants.CHART_LINE_WIDTH
            circleRadius = Constants.CHART_CIRCLE_RADIUS
            valueTextSize = Constants.CHART_VALUE_TEXT_SIZE
            circleHoleRadius = Constants.CHART_CIRCLE_HOLE_RADIUS
            color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
            circleHoleColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
            setCircleColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER

        }

        val dataSets = mutableListOf<ILineDataSet>().apply { add(lineDataSet) }

        chart.animateX(500)
        chart.data = LineData(dataSets).apply { isHighlightEnabled = false }
    }
}
