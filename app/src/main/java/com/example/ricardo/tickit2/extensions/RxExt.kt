package com.example.ricardo.tickit2.extensions

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ricardo on 2017/11/27.
 */

fun <T> Single<T>.applySchedulers(): Single<T> = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

operator fun CompositeDisposable.plusAssign(disposable: Disposable){
    add(disposable)
}

fun <T> Single<T>.subscribeBy(
        onError: ((Throwable) -> Unit)? = null,
        onSuccess: (T) -> Unit
): Disposable = subscribe(onSuccess,{ onError?.invoke(it)})