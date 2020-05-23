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

    override fun providePresenter() = HomePresenter(ServiceLocator.repository)
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
        presenter.getCountryCurrency(application.getCountryCode())
    }

    override fun setHomeCountryCurrencyCode(code: String) {
        showToast("saving: $code")
        application.homeCountryCurrencyCode = code
    }

    override fun showLoading() {
        loadingManager?.showLoading()
    }

    override fun hideLoading() {
        loadingManager?.hideLoading()
    }
}
