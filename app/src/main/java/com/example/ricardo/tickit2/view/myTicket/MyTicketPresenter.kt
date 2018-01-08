package com.example.ricardo.tickit2.view.myTicket

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.repository.OrderRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.getLocalUser
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ricardo on 2017/12/29.
 */

class MyTicketPresenter(val view: MyTicketView, val repository: OrderRepository):BasePresenter{
    var mUserDao: GDUserDao? = null

    protected var subscriptins = CompositeDisposable()

    override fun start() {

    }

    fun onRefresh(){
        val user = getLocalUser(mUserDao!!)
        getOrder(user!!)
    }

    fun getOrder(user: User){
        subscriptins += repository.getOrder(user)
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally{ view.refresh = false }
                .subscribeBy (
                        onSuccess = view::show,
                        onError = view::showError
                )
    }

    override fun onViewDestroyed() {
        subscriptins.dispose()
    }
}