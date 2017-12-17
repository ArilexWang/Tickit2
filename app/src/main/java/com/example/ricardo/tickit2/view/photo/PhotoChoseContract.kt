package com.example.ricardo.tickit2.view.photo

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/12/17.
 */
interface PhotoChoseContract {

    interface Presenter : BasePresenter {

        //更新用户信息
        fun updateUserInfo(avatarPath: String)
    }
}