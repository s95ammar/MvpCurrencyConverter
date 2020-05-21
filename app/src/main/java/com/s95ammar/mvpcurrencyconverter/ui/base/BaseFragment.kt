package com.s95ammar.mvpcurrencyconverter.ui.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BaseContract.Presenter<*>>
    : Fragment(), BaseContract.View {

    protected lateinit var presenter: P

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = providePresenter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
        presenter.detach()
    }

    override fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    abstract fun providePresenter(): P

}
