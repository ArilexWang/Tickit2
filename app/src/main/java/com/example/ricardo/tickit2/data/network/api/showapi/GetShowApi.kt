package com.example.ricardo.tickit2.data.network.api.showapi

import com.example.ricardo.tickit2.data.dto.ShowDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Ricardo on 2017/12/26.
 */
interface GetShowApi {

    @GET("getAllShow/")
    fun getShow(
//            @Field("category") category: Int
    ): Single<List<ShowDto>>
}