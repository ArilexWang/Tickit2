package com.example.ricardo.tickit2.view.admin.set

import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Show

/**
 * Created by Ricardo on 2018/1/6.
 */
interface SetView {
    var refresh: Boolean
    fun onSuccess(items: List<BannerPicture>)
    fun onError(error: Throwable)

    fun deleteSuccess(success: String)
    fun deleteError(error: Throwable)

    fun onShowSuccess(items: List<Show>)
    fun onShowError(error: Throwable)

}