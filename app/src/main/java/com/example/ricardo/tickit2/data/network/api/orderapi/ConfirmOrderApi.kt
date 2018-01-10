package com.example.ricardo.tickit2.data.network.api.orderapi

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2018/1/11.
 */


interface ConfirmOrderApi {
    @FormUrlEncoded
    @POST("confirmOrderByIDWithAdminID/")
    fun confirmOrder(
            @Field("adminID") studentID: String,
            @Field("password") password: String,
            @Field("orderID") orderID: String
    ): Single<String>
}