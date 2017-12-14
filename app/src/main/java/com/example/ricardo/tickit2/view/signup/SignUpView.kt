package com.example.ricardo.tickit2.view.signup

import com.example.ricardo.tickit2.data.dto.UserDto
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.greendao.gen.GDUserDao

/**
 * Created by Ricardo on 2017/11/12.
 */

interface SignUpView {

    fun onSuccess(items: List<User>)
    fun onError(error: Throwable)

    fun saveUserToLocal(item: User, userDao: GDUserDao)

}