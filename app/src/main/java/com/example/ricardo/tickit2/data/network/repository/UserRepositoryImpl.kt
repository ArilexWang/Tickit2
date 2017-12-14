package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.dto.UserDto
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.api.SigninApi
import com.example.ricardo.tickit2.data.network.api.SignupApi
import com.example.ricardo.tickit2.data.network.provider.retrofit
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/11/23.
 */

class UserRepositoryImpl : UserRepository{

    val api = retrofit.create(SignupApi::class.java)
    val signInApi = retrofit.create(SigninApi::class.java)

    override fun postAccount(user: User): Single<List<User>> = api.postAccount(
            studentID = user.id.toLong(),
            nickname = user.nickName,
            realName = user.realName,
            mobileNumber = user.mobileNumber.toLong(),
            password = user.password
    ).map {
        it.map(::User)
    }

    override fun signIn(studentID: String, password: String): Single<List<User>> = signInApi.postAccount(
            studentID.toLong(),
            password).map {
        it.map(::User)
    }

}