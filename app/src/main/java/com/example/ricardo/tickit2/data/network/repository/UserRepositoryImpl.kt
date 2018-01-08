package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.api.userapi.SigninApi
import com.example.ricardo.tickit2.data.network.api.userapi.SignupApi
import com.example.ricardo.tickit2.data.network.api.userapi.UpdateApi
import com.example.ricardo.tickit2.data.network.provider.retrofit
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/11/23.
 */

class UserRepositoryImpl : UserRepository{

    val api = retrofit.create(SignupApi::class.java)
    val signInApi = retrofit.create(SigninApi::class.java)
    val updateApi = retrofit.create(UpdateApi::class.java)

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

    override fun updateUserInfo(user: User): Single<List<User>> = updateApi.UpdateUserMsg(
            studentID = user.id.toLong(),
            nickname = user.nickName,
            realName = user.realName,
            mobileNumber = user.mobileNumber.toLong(),
            password = user.password,
            avatar = user.avatar
    ).map {
        it.map(::User)
    }

}