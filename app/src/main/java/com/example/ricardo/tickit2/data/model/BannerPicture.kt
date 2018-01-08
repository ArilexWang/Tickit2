package com.example.ricardo.tickit2.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.ricardo.tickit2.data.dto.BannerPicDto
import com.youth.banner.Banner

import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo on 2017/12/26.
 */
@SuppressLint("ParceCreator")
@Parcelize
class BannerPicture (
    var id: String,
    var picPath: String,
    var targetPath: String,
    var isOnDisplay: Boolean
): Parcelable{
    constructor():this("","","",true)
    constructor(dto: BannerPicDto):this(
            id = dto.pk.toString(),
            picPath = dto.fields.pictureURL,
            targetPath = dto.fields.descriptionURL,
            isOnDisplay = dto.fields.isOnDisplay
    )
    constructor(banner: BannerPicture):this(
            id = banner.id,
            picPath = banner.picPath,
            targetPath = banner.targetPath,
            isOnDisplay = banner.isOnDisplay
    )
}
