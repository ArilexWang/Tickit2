package com.example.ricardo.tickit2.view.admin.banner

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ricardo on 2018/1/6.
 */

class BannerPresenter(val view: BannerView,val repository: BannerPicRepository):BasePresenter{
    protected var subscriptions = CompositeDisposable()
    override fun start() {
        getBannerPic()
    }

    public fun onRefresh(){
        getBannerPic()
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


    override fun onViewDestroyed() {

    }

}