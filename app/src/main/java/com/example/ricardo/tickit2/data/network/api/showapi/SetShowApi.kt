package com.example.ricardo.tickit2.data.network.api.showapi

import com.example.ricardo.tickit2.data.dto.BannerPicDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2018/1/8.
 */
interface SetShowApi {
    @FormUrlEncoded
    @POST("setShowByID/")
    fun setShow(
            @Field("studentID") studentID: String,
            @Field("password") password: String,
            @Field("showID") showID: String,
            @Field("showName") showName: String,
            @Field("showAvatar") showAvatar:String,
            @Field("descriptionURL") descriptionURL: String,
            @Field("showTime") showTime: String,
            @Field("category") category: Int,
            @Field("isOnSale") isOnDisplay: Int
    ): Single<List<BannerPicDto>>
}