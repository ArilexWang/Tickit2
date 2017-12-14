package com.example.ricardo.tickit2.view.signup

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.base.BaseView
import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/11/13.
 */

//接口类，将所用函数定义在这里，带上注释
interface SignUpContract {


    interface Presenter : BasePresenter {

        //将账户信息发送到服务器
        fun postAccount(user: User)
    }
}