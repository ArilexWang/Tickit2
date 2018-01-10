package com.example.ricardo.tickit2.view.category

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import com.example.ricardo.tickit2.view.category.CategoryView
import io.reactivex.disposables.CompositeDisposable


class CategoryPresenter(val repository: ShowRepository,val view: CategoryView):BasePresenter{


    protected var subscriptions = CompositeDisposable()

    override fun start() {
        getShow()
    }

    fun getShow(){
        subscriptions += repository.getNewShow()
                .applySchedulers()
                .subscribeBy (
                        onSuccess = view::onShowSuccess,
                        onError = view::onShowError
                )
    }

    override fun onViewDestroyed() {

    }

}

