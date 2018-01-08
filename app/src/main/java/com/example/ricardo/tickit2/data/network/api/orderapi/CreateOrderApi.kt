package com.example.ricardo.tickit2.data.network.api.orderapi

import com.example.ricardo.tickit2.data.dto.OrderDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2017/12/30.
 */
interface CreateOrderApi{
    @FormUrlEncoded
    @POST("createOrder/")
    fun createOrder(
            @Field("studentID") studentID: String,
            @Field("password") password: String,
            @Field("ticketTypeID") ticketTypeID: Long
    ): Single<List<OrderDto>>
}