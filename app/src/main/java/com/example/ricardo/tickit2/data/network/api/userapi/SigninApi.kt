package com.example.ricardo.tickit2.data.network.api.userapi

import com.example.ricardo.tickit2.data.dto.UserDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SigninApi{
    @FormUrlEncoded
    @POST("userSignInByID/")
    fun postAccount(
            @Field("studentID") studentID: Long,
            @Field("password") password: String
    ): Single<List<UserDto>>
}