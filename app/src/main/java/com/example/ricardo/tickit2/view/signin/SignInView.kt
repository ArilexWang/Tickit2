package com.example.ricardo.tickit2.view.signin

import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/11/17.
 */

interface SignInView{

    fun onSuccess(items: List<User>)

    fun onError(error: Throwable)
}