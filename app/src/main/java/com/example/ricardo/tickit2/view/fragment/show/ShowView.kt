package com.example.ricardo.tickit2.view.fragment.show

import com.example.ricardo.tickit2.data.model.Show

/**
 * Created by Ricardo on 2018/1/9.
 */
interface ShowView{
    fun onSuccess(items: List<Show>)
    fun onError(error: Throwable)
}