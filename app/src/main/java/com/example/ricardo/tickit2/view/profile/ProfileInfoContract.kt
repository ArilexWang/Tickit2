package com.example.ricardo.tickit2.view.profile

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.greendao.gen.GDUserDao

/**
 * Created by Ricardo on 2017/12/17.
 */

//接口类，将所用函数定义在这里
interface ProfileInfoContract {

    interface Presenter : BasePresenter {
        fun getLocalAvatar(): String?
    }
}