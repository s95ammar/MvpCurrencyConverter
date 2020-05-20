package com.s95ammar.mvpcurrencyconverter.ui.currencieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.s95ammar.mvpcurrencyconverter.R

class CurrenciesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currencies_list, container, false)
    }
}
