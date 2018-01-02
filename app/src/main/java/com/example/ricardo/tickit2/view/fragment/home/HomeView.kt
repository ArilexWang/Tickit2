package com.example.ricardo.tickit2.view.fragment.home

import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/12/26.
 */
interface HomeView{
    fun onSuccess(items: List<BannerPicture>)
    fun onError(error: Throwable)

    fun onShowSuccess(items: List<Show>)
    fun onShowError(error: Throwable)

}