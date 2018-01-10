package com.example.ricardo.tickit2.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.ricardo.tickit2.data.dto.UserDto
import com.example.ricardo.tickit2.data.entity.GDUser
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParceCreator")
@Parcelize
class User(
        var id: String,
        var nickName: String,
        var realName: String,
        var mobileNumber: String,
        var password: String,
        var avatar: String,
        var isSuperUser: Boolean,
        var isAdmin: Boolean,
        var vipPoint: Int

) :Parcelable {
    constructor(dto: UserDto): this(
            id = dto.pk,
            nickName = dto.fields.nickname,
            realName = dto.fields.realName,
            mobileNumber = dto.fields.mobileNumber,
            password = dto.fields.password,
            avatar = dto.fields.avatar,
            isSuperUser = dto.fields.isSuperuser,
            isAdmin = dto.fields.isAdmin,
            vipPoint = dto.fields.vipPoint
    )
    constructor(id: String, nickName: String,realName: String, mobileNumber: String,password: String):
            this(id, nickName, realName, mobileNumber, password, "",false,false,0)

    constructor():this("","","","","")

    constructor(gdUser: GDUser): this(
            id = gdUser.id,
            nickName = gdUser.nickName,
            realName = gdUser.realName,
            mobileNumber = gdUser.mobileNumber,
            password = gdUser.password,
            avatar = gdUser.avatar,
            isAdmin = gdUser.isAdmin,
            isSuperUser = gdUser.isSuperUser,
            vipPoint = gdUser.vipPoint
    )
}