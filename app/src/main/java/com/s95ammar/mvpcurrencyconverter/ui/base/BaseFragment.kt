package com.s95ammar.mvpcurrencyconverter.ui.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BaseContract.Presenter<*>>
    : Fragment(), BaseContract.View {

    protected var presenter: P? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = providePresenter()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (presenter != null) {
            presenter?.unsubscribe()
            presenter?.detach()
        }
    }

    abstract fun providePresenter(): P

}
