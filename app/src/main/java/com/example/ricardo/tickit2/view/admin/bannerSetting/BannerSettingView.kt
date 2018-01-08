package com.example.ricardo.tickit2.view.admin.bannerSetting

import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.model.Show

/**
 * Created by Ricardo on 2018/1/7.
 */
interface BannerSettingView {

    fun onSuccess(items: List<BannerPicture>)

    fun onError(error: Throwable)

    fun createSuccess(items: List<BannerPicture>)
    fun createFaile(error: Throwable)

    fun setShowSuccess(items: List<Show>)
    fun setShowError(error: Throwable)
}