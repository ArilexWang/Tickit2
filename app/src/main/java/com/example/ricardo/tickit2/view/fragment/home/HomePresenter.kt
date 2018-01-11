package com.example.ricardo.tickit2.view.fragment.home

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ricardo on 2017/12/26.
 */

class HomePresenter(val view: HomeView,val repository: BannerPicRepository, val showRepository: ShowRepository):BasePresenter{

    protected var subscriptions = CompositeDisposable()

    override fun start() {
        getBannerPic()
        getShow()
    }

    fun onRefresh(){
        getBannerPic()
        getShow()
    }

    fun getBannerPic() {
        subscriptions += repository.getBannerPic()
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally{ view.refresh = false }
                .subscribeBy (
                        onSuccess = view::onSuccess,

                        onError = view::onError
                )
    }
    fun getShow(){

        subscriptions += showRepository.getNewShow()
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally{ view.refresh = false }
                .subscribeBy (
                    onSuccess = view::onShowSuccess,
                    onError = view::onShowError
                )
    }


    override fun onViewDestroyed() {
        subscriptions.dispose()
    }
}