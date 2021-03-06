package com.s95ammar.mvpcurrencyconverter.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.s95ammar.mvpcurrencyconverter.Constants.NONE
import com.s95ammar.mvpcurrencyconverter.R
import com.s95ammar.mvpcurrencyconverter.ServiceLocator
import com.s95ammar.mvpcurrencyconverter.setSelectionListener
import com.s95ammar.mvpcurrencyconverter.ui.base.BaseFragment
import com.s95ammar.mvpcurrencyconverter.ui.viewentities.RateViewEntity
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment<SettingsContract.Presenter>(), SettingsContract.View {

    override fun providePresenter() = SettingsPresenter(
        ServiceLocator.repository,
        application.homeCountryCode,
        application.baseCurrencyCode,
        application.homeCurrencyCode
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
    }

    override fun showLoading() {
        loadingManager?.showLoading()
    }

    override fun hideLoading() {
        loadingManager?.hideLoading()
    }

    override fun setUpSpinners(values: List<RateViewEntity>) {
        val currenciesList = values.toMutableList().map { it.fromCode + " - " + it.fromName }
        setUpHomeCountryCurrencySpinner(currenciesList)
        setUpBaseCountryCurrencySpinner(currenciesList)
    }

    private fun setUpHomeCountryCurrencySpinner(currenciesList: List<String>) {
        val dataList = mutableListOf(NONE) + currenciesList
        home_country_currency_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, dataList)
        application.homeCurrencyCode?.let { code ->
            home_country_currency_spinner.setSelection(dataList.indexOfFirst { it.startsWith(code) })
        }

        home_country_currency_spinner.setSelectionListener<String> { selection ->
            if (selection != NONE) application.homeCurrencyCode = selection.split(" ").first()
        }
    }

    private fun setUpBaseCountryCurrencySpinner(currenciesList: List<String>) {
        base_currency_spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, currenciesList)
        base_currency_spinner.setSelection(currenciesList.indexOfFirst { it.startsWith(application.baseCurrencyCode) })
        base_currency_spinner.setSelectionListener<String> { selection ->
            application.baseCurrencyCode = selection.split(" ").first()
        }
    }

}
