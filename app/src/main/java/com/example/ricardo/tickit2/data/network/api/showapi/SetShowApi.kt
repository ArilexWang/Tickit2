package com.example.ricardo.tickit2.data.network.api.showapi

import com.example.ricardo.tickit2.data.dto.BannerPicDto
import com.example.ricardo.tickit2.data.dto.ShowDto
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
            @Field("name") showName: String,
            @Field("avatar") showAvatar:String,
            @Field("description") descriptionURL: String,
            @Field("showTime") showTime: String,
            @Field("expiredTime") expiredTime: String,
            @Field("expiredFetchTime") expiredFetchTime: String,
            @Field("category") category: Int,
            @Field("isOnSale") isOnSale: Int,
            @Field("isRestricted") isRestricted: Int,
            @Field("restrictionNum") restrictionNum: Int,
            @Field("remainedAmount") remainAmount: Int
    ): Single<List<ShowDto>>
}