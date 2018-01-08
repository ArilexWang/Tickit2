package com.example.ricardo.tickit2.view.admin.set

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.OrderRepository
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

class SetPresenter(val view: SetView, val repository: BannerPicRepository, val showRepository: ShowRepository,val orderRepository: OrderRepository):BasePresenter{
    protected var subscriptions = CompositeDisposable()

    var mUserDao: GDUserDao? = null

    var from:String? = null

    override fun start() {
        if (from == SHOW_INTENT) getShow()
        else if(from == BANNER_INTENT) getBannerPic()
        else if(from == ORDER_INTENT) getOrder()
    }

    public fun onRefresh(){
        if (from == SHOW_INTENT) getShow()
        else if(from == BANNER_INTENT) getBannerPic()
        else if(from == ORDER_INTENT) getOrder()
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

    fun getOrder(){
        val user = getLocalUser(mUserDao!!)
        subscriptions += orderRepository.getAllOrder(user!!)
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally{ view.refresh = false }
                .subscribeBy(
                        onSuccess = view::onOrderSuccess,
                        onError = view::onError
                )
    }

    fun getOrderByKey(key: String){
        val user = getLocalUser(mUserDao!!)
        subscriptions += orderRepository.getOrderByKey(user!!,key)
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally{ view.refresh = false }
                .subscribeBy(
                        onSuccess = view::onOrderSuccess,
                        onError = view::onOrderError
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

    fun onSearchChanged(key: String){
        if (key != null){
            getOrderByKey(key!!)
        } else{
            println("nullll")
            getOrder()
        }

    }



    override fun onViewDestroyed() {

    }

    companion object {
        private const val BANNER_INTENT = "MAIN_BANNER"
        private const val SHOW_INTENT = "MAIN_SHOW"
        private const val ORDER_INTENT = "MAIN_ORDER"
    }

}