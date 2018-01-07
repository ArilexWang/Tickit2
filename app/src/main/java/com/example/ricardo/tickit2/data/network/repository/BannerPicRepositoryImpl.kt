package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.api.GetBannerApi
import com.example.ricardo.tickit2.data.network.api.SetBannerApi
import com.example.ricardo.tickit2.data.network.provider.retrofit
import com.example.ricardo.tickit2.extensions.toInt
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/26.
 */
class BannerPicRepositoryImpl: BannerPicRepository {
    val bannerPicApi = retrofit.create(GetBannerApi::class.java)
    val setBannerApi = retrofit.create(SetBannerApi::class.java)

    override fun setBannerPic(user: User, banner: BannerPicture): Single<List<BannerPicture>> = setBannerApi.setBanner(
            studentID = user.id,
            password = user.password,
            bannerID = banner.id,
            picURL = banner.picPath,
            descriptionURL = banner.targetPath,
            isOnDisplay = banner.isOnDisplay.toInt()
    ).map { it.map(::BannerPicture) }

    override fun getBannerPic(): Single<List<BannerPicture>> = bannerPicApi.getBannerPic().map { it.map(::BannerPicture) }
}