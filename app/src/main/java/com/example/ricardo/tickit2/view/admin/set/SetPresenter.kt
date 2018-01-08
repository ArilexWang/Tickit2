package com.example.ricardo.tickit2.view.admin.set

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.getLocalUser
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ricardo on 2018/1/6.
 */

class SetPresenter(val view: SetView, val repository: BannerPicRepository, val showRepository: ShowRepository):BasePresenter{
    protected var subscriptions = CompositeDisposable()

    var mUserDao: GDUserDao? = null

    var from:String? = null

    override fun start() {
        if (from == "MAIN_SHOW") getShow()
        else getBannerPic()
    }

    public fun onRefresh(){
        if (from == "MAIN_SHOW") getShow()
        else getBannerPic()
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


    fun deleteBanner(banner: BannerPicture){
        val user = getLocalUser(mUserDao!!)
        subscriptions += repository.deleteBannerPic(user!!,banner)
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally{ view.refresh = false }
                .subscribeBy(
                        onSuccess = view::deleteSuccess,
                        onError = view::deleteError
                )
    }

    override fun onViewDestroyed() {

    }

}