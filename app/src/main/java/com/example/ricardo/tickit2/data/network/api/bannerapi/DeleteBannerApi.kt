package com.example.ricardo.tickit2.data.network.api.bannerapi

import com.example.ricardo.tickit2.data.dto.BannerPicDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2018/1/8.
 */
interface DeleteBannerApi {
    @FormUrlEncoded
    @POST("deleteBannerPictureByID/")
    fun deleteBanner(
            @Field("studentID") studentID: String,
            @Field("password") password: String,
            @Field("bannerPictureID") bannerID: String

    ): Single<String>
}