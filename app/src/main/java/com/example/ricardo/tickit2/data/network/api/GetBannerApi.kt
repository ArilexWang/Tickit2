package com.example.ricardo.tickit2.data.network.api

import com.example.ricardo.tickit2.data.dto.BannerPicDto
import com.example.ricardo.tickit2.data.model.BannerPicture
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ricardo on 2017/12/26.
 */
interface GetBannerApi {
    @GET("getBannerPicture/")
    fun getBannerPic():Single<List<BannerPicDto>>

}