package com.example.ricardo.tickit2.data.network.api

import com.example.ricardo.tickit2.data.dto.OrderDto
import com.example.ricardo.tickit2.data.dto.TicketDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2017/12/30.
 */
interface GetOrderApi {
    @FormUrlEncoded
    @POST("getOrderByStudentID/")
    fun getOrder(
            @Field("studentID") studentID: String,
            @Field("password") password: String
    ): Single<List<TicketDto>>
}