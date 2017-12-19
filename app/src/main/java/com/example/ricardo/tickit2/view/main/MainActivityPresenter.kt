package com.example.ricardo.tickit2.view.main

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.network.repository.UserRepository
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ricardo on 2017/12/19.
 */

class MainActivityPresenter(val view:MainView,val respository: UserRepository): BasePresenter{

    protected var subscriptins = CompositeDisposable()
    var mUserDao: GDUserDao? = null
    override fun start() {
        getLocalUser(mUserDao!!)
    }

    fun getLocalUser(userDao: GDUserDao){
        val db = userDao!!.queryBuilder()

        val list = db.list()

        if (!list.isEmpty()){
            val id = list[0].id
            val password = list[0].password
            postAccount(id,password)
        }


    }

    fun postAccount(studentID: String, password: String) {
        subscriptins += respository.signIn(studentID,password)
                .applySchedulers()
                .subscribeBy (
                        onSuccess = view::onSuccess,
                        onError = view::onError
                )
    }


    override fun onViewDestroyed() {

    }
}