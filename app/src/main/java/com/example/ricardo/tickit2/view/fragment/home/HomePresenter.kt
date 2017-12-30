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
        getShow2()
    }

    fun getBannerPic() {
        subscriptions += repository.getBannerPic()
                .applySchedulers()
                .subscribeBy (
                        onSuccess = view::onSuccess,
                        onError = view::onError
                )
    }
    fun getShow(){
        val category: Int = 4
        subscriptions += showRepository.getNewShow(category)
                .applySchedulers()
                .subscribeBy (
                    onSuccess = view::onShowSuccess,
                    onError = view::onShowError
                )
    }

    fun getShow2(){
        val category: Int = 0
        subscriptions += showRepository.getNewShow(category)
                .applySchedulers()
                .subscribeBy (
                        onSuccess = view::onShow2Success,
                        onError = view::onShow2Error
                )
    }


    override fun onViewDestroyed() {
        subscriptions.dispose()
    }
}