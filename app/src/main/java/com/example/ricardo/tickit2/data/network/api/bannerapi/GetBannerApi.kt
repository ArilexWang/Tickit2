package com.example.ricardo.tickit2.data.network.api.bannerapi

import com.example.ricardo.tickit2.data.dto.BannerPicDto
import com.example.ricardo.tickit2.data.model.BannerPicture
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GetBannerApi {
    @GET("getAllBannerPicture/")
    fun getBannerPic():Single<List<BannerPicDto>>

}