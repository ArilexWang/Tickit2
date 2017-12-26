package com.example.ricardo.tickit2.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.ricardo.tickit2.data.dto.BannerPicDto

import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo on 2017/12/26.
 */
@SuppressLint("ParceCreator")
@Parcelize
class BannerPicture (
    var picPath: String,
    var targetPath: String
): Parcelable{
    constructor():this("","")
    constructor(dto: BannerPicDto):this(
            picPath = dto.fields.pictureURL,
            targetPath = dto.fields.descriptionURL
    )
}
