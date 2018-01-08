package com.example.ricardo.tickit2.view.admin.bannerSetting

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.getLocalUser
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ricardo on 2018/1/6.
 */

class BannerSettingPresenter(val view: BannerSettingView,val repository: BannerPicRepository): BasePresenter{
    var mUserDao: GDUserDao? = null

    protected var subscriptins = CompositeDisposable()

    override fun start() {

    }

    fun setBanner(banner:BannerPicture){
        val user = getLocalUser(mUserDao!!)
        subscriptins += repository.setBannerPic(user!!,banner)
                .applySchedulers()
                .subscribeBy (
                        onSuccess = view::onSuccess,
                        onError = view::onError
                )
    }


    fun createBanner(banner: BannerPicture){
        val user = getLocalUser(mUserDao!!)
        subscriptins += repository.createBannerPic(user!!,banner)
                .applySchedulers()
                .subscribeBy(
                        onSuccess = view::createSuccess,
                        onError = view::createFaile
                )
    }





    override fun onViewDestroyed() {

    }
}