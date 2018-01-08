package com.example.ricardo.tickit2.data.network.api.orderapi

import com.example.ricardo.tickit2.data.dto.TicketDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2018/1/8.
 */
interface GetAllOrderApi {
    @FormUrlEncoded
    @POST("getAllOrder/")
    fun getAllOrder(
            @Field("studentID") studentID: String,
            @Field("password") password: String
    ): Single<List<TicketDto>>
}