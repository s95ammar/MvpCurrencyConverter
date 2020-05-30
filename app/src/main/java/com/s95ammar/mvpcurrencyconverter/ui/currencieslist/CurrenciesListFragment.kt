package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.s95ammar.mvpcurrencyconverter.Constants
import com.s95ammar.mvpcurrencyconverter.R
import com.s95ammar.mvpcurrencyconverter.ServiceLocator
import com.s95ammar.mvpcurrencyconverter.ui.activity.LoadingManager
import com.s95ammar.mvpcurrencyconverter.ui.base.BaseFragment
import com.s95ammar.mvpcurrencyconverter.ui.currencieslist.adapter.CurrenciesListAdapter
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateViewEntity
import kotlinx.android.synthetic.main.fragment_currencies_list.*
import org.joda.time.LocalDate

class CurrenciesListFragment : BaseFragment<CurrenciesListPresenter>(), CurrenciesListContract.View {

    private val adapter = CurrenciesListAdapter()

    override fun providePresenter() = CurrenciesListPresenter(
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
        return inflater.inflate(R.layout.fragment_currencies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        list_layout_swipe_to_refresh.setOnRefreshListener { presenter.onRefresh() }
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
    }

    override fun showLoading() {
        loadingManager?.showLoading()
    }

    override fun hideLoading() {
        loadingManager?.hideLoading()
        list_layout_swipe_to_refresh.isRefreshing = false
    }

    override fun setFromCode(value: String) {
        rates_for_text_view.text = resources.getString(R.string.rates_for_code, value)
    }

    override fun setDate(value: String) {
        val formattedDate = LocalDate(value).toString(Constants.DATE_PATTERN_FULL)
        rates_for_date_text_view.text = resources.getString(R.string.rates_for_date, formattedDate)
    }

    override fun displayRates(rates: List<RateViewEntity>) {
        adapter.items = rates
        adapter.notifyDataSetChanged()
    }
}
