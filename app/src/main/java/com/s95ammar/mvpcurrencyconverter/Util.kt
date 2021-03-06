package com.s95ammar.mvpcurrencyconverter

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun Disposable.disposeBy(compositeDisposable: CompositeDisposable): Disposable =
    apply { compositeDisposable.add(this) }


fun Any.logcat(functionName: String, msg: String? = "") {
    Log.d("LOG_MVP_CURR", "${this.javaClass.simpleName}: $functionName: $msg")
}

fun <T> Single<T>.subIoObserveMain(doFinally: ()-> Unit = {}, onError: (Throwable) -> Unit = {}, onSuccess: (T) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doFinally(doFinally)
        .subscribe(
            onSuccess,
            onError
        )
}

inline fun <reified T> Spinner.setSelectionListener(crossinline listener: (selection: T) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            (parent?.selectedItem as? T)?.let { selection ->
                listener.invoke(selection)
            }
        }
    }

}

data class Header(val name: String, val value: String)