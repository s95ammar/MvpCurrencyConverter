package com.s95ammar.mvpcurrencyconverter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s95ammar.mvpcurrencyconverter.R
import com.s95ammar.mvpcurrencyconverter.ServiceLocator
import com.s95ammar.mvpcurrencyconverter.ui.base.BaseFragment


class HomeFragment : BaseFragment<HomeContract.Presenter>(), HomeContract.View {

    override fun providePresenter() = HomePresenter(ServiceLocator.repository)

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
}
