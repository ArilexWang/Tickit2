package com.example.ricardo.tickit2.data.network.api.orderapi

import com.example.ricardo.tickit2.data.dto.OrderDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2018/1/11.
 */
interface CancelOrderByUserApi {
    @FormUrlEncoded
    @POST("cancelOrderByID/")
    fun cancelOrderByUser(
            @Field("studentID") studentID: String,
            @Field("password") password: String,
            @Field("orderID") orderID: String
    ): Single<String>
}