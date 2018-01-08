package com.example.ricardo.tickit2.data.network.api.orderapi

import com.example.ricardo.tickit2.data.dto.TicketDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2018/1/9.
 */
interface GetOrderByKeyApi {
    @FormUrlEncoded
    @POST("getOrderByPartIDWithAdminID/")
    fun getOrderByKey(
            @Field("adminID") studentID: String,
            @Field("password") password: String,
            @Field("partID") key: String
    ): Single<List<TicketDto>>
}