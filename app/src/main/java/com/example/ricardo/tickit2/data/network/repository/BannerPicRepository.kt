package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.provider.Provider
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/26.
 */
interface BannerPicRepository {
    fun getBannerPic(): Single<List<BannerPicture>>
    fun setBannerPic(user: User,banner: BannerPicture): Single<List<BannerPicture>>

    companion object : Provider<BannerPicRepository>() {
        override fun creator() =  BannerPicRepositoryImpl()
    }
}