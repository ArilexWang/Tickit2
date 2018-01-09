package com.example.ricardo.tickit2.view.fragment.show

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import com.example.ricardo.tickit2.view.common.BaseActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ricardo on 2018/1/9.
 */

class ShowPresenter(val view: ShowView,val repository: ShowRepository):BasePresenter{

    protected var subscriptions = CompositeDisposable()

    override fun start() {
        getShow()
    }

    fun getShow(){
        subscriptions += repository.getNewShow()
                .applySchedulers()
                .subscribeBy (
                        onSuccess = view::onSuccess,
                        onError = view::onError
                )
    }

    override fun onViewDestroyed() {

    }

}