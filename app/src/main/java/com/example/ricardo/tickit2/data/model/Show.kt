package com.example.ricardo.tickit2.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.ricardo.tickit2.data.dto.ShowDto
import com.example.ricardo.tickit2.extensions.toInt
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo on 2017/12/26.
 */

@SuppressLint("ParceCreator")
@Parcelize
class Show(
        var id: Long,
        var parentEventID: Long,
        var name: String,
        var avatarPath: String,
        var descriptionPath: String,
        var is_OnSale: Boolean,
        var category: Int,
        var showTime: String,
        var expiredTime: String,
        var isRestricted: Boolean,
        var restrictionNum: Int
): Parcelable{
    constructor(dto:ShowDto):this(
            id = dto.pk.toLong(),
            parentEventID = dto.fields.parentEventID.toLong(),
            name = dto.fields.name,
            avatarPath = dto.fields.avatar,
            descriptionPath = dto.fields.description,
            is_OnSale = dto.fields.isOnSale,
            category = dto.fields.category,
            showTime = dto.fields.showTime,
            expiredTime = dto.fields.expiredTime,
            isRestricted = dto.fields.isRestricted,
            restrictionNum = dto.fields.restrictionNum
    )
}