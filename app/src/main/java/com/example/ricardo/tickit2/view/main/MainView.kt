package com.example.ricardo.tickit2.view.main

import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/12/19.
 */

interface MainView{
    fun onSuccess(items: List<User>)

    fun onError(error: Throwable)
}