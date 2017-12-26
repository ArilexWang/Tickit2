package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.network.api.GetBannerApi
import com.example.ricardo.tickit2.data.network.provider.retrofit
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/26.
 */
class BannerPicRepositoryImpl: BannerPicRepository {
    val bannerPicApi = retrofit.create(GetBannerApi::class.java)

    override fun getBannerPic(): Single<List<BannerPicture>> = bannerPicApi.getBannerPic().map { it.map(::BannerPicture) }
}