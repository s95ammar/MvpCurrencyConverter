package com.s95ammar.mvpcurrencyconverter.ui.base

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.s95ammar.mvpcurrencyconverter.App
import com.s95ammar.mvpcurrencyconverter.Errors
import com.s95ammar.mvpcurrencyconverter.R
import com.s95ammar.mvpcurrencyconverter.ui.activity.LoadingManager

abstract class BaseFragment<P : BaseContract.Presenter<*>> : Fragment(), BaseContract.View {

    protected lateinit var presenter: P

    protected var loadingManager: LoadingManager? = null
    protected val application
        get() = requireActivity().applicationContext as App


    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadingManager = context as? LoadingManager
        presenter = providePresenter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
        presenter.detach()
    }

    override fun setHomeCountryCurrencyCode(code: String) {
        application.homeCurrencyCode = code
    }

    protected fun showToast(@StringRes resId: Int) {
        Toast.makeText(requireContext(), resId, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onError(error: Int) {
        when (error) {
            Errors.CONNECTION_ERROR -> showToast(R.string.error_connection_failed)
            Errors.COUNTRY_UNAVAILABLE_ERROR -> showToast(R.string.error_country_unavailable)
            Errors.INTERNAL_SERVER_ERROR -> showToast(R.string.error_server)
            else -> showToast(R.string.error_unknown)
        }
    }

    abstract fun providePresenter(): P

}
