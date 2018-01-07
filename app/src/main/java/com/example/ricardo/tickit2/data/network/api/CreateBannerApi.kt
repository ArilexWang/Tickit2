package com.example.ricardo.tickit2.data.network.api

import android.view.Display
import com.example.ricardo.tickit2.data.dto.BannerPicDto
import com.example.ricardo.tickit2.data.dto.OrderDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2018/1/7.
 */
interface CreateBannerApi {
    @FormUrlEncoded
    @POST("createBannerPicture/")
    fun createBanner(
            @Field("studentID") studentID: String,
            @Field("password") password: String,
            @Field("pictureURL") picUrl: String,
            @Field("descriptionURL") descriptionUrl: String,
            @Field("isOnDisplay") isOnDisplay: Int
    ): Single<List<BannerPicDto>>
}