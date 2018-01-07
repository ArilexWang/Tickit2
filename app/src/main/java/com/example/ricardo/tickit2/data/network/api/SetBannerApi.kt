package com.example.ricardo.tickit2.data.network.api

import com.example.ricardo.tickit2.data.dto.BannerPicDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.io.FileDescriptor

/**
 * Created by Ricardo on 2018/1/7.
 */
interface SetBannerApi {
    @FormUrlEncoded
    @POST("setBannerPictureByID/")
    fun setBanner(
            @Field("studentID") studentID: String,
            @Field("password") password: String,
            @Field("bannerPictureID") bannerID: String,
            @Field("pictureURL") picURL: String,
            @Field("descriptionURL") descriptionURL: String,
            @Field("isOnDisplay") isOnDisplay: Int
    ):Single<List<BannerPicDto>>
}