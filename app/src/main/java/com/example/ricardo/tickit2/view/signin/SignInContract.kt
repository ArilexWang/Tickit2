package com.example.ricardo.tickit2.view.signin

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.base.BaseView
import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/11/17.
 */

//接口类，将所用函数定义在这里
interface SignInContract {

    interface Presenter : BasePresenter {
        fun postAccount(userName:String, password:String)
    }
}