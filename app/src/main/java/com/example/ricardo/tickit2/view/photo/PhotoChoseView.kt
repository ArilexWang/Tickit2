package com.example.ricardo.tickit2.view.photo

import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/12/17.
 */


interface PhotoChoseView{
    fun postAvatarSuccess(path: String?)
    fun postAvatarError(path: String?)

    fun onSuccess(items: List<User>)
    fun onError(error: Throwable)

}