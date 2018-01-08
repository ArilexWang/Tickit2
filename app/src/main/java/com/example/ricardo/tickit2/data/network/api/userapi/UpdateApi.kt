package com.example.ricardo.tickit2.data.network.api.userapi

import com.example.ricardo.tickit2.data.dto.UserDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Ricardo on 2017/12/18.
 */
interface UpdateApi {
    @FormUrlEncoded
    @POST("setUserByID/")
    fun UpdateUserMsg(
            @Field("nickname") nickname: String,
            @Field("studentID") studentID: Long,
            @Field("realName") realName: String,
            @Field("mobileNumber") mobileNumber: Long,
            @Field("password") password: String,
            @Field("avatar") avatar: String
    ): Single<List<UserDto>>
}