package com.example.ricardo.tickit2.view.admin.bannerSetting

import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Order

/**
 * Created by Ricardo on 2018/1/7.
 */
interface BannerSettingView {

    fun onSuccess(items: List<BannerPicture>)

    fun onError(error: Throwable)

}