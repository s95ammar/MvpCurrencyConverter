package com.s95ammar.mvpcurrencyconverter

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.disposeBy(compositeDisposable: CompositeDisposable): Disposable =
    apply { compositeDisposable.add(this) }


fun Any.logcat(functionName: String, msg: String? = "") {
    Log.d("LOG_MVP_CURR", "${this.javaClass.name}: $functionName: $msg")
}

data class Header(val name: String, val value: String)