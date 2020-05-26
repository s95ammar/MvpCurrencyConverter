package com.s95ammar.mvpcurrencyconverter.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s95ammar.mvpcurrencyconverter.R
import com.s95ammar.mvpcurrencyconverter.ServiceLocator
import com.s95ammar.mvpcurrencyconverter.ui.activity.LoadingManager
import com.s95ammar.mvpcurrencyconverter.ui.base.BaseFragment
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

    override fun setHomeCountryCurrencyCode(code: String) {
        showToast("saving: $code")
        application.homeCurrencyCode = code
    }

    override fun showLoading() {
        loadingManager?.showLoading()
    }

    override fun hideLoading() {
        loadingManager?.hideLoading()
        home_layout_swipe_to_refresh.isRefreshing = false
    }
}
