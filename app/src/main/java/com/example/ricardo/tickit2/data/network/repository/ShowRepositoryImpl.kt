package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.api.showapi.GetShowApi
import com.example.ricardo.tickit2.data.network.api.showapi.SetShowApi
import com.example.ricardo.tickit2.data.network.provider.retrofit
import com.example.ricardo.tickit2.extensions.toInt
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/26.
 */
class ShowRepositoryImpl: ShowRepository{
    val showApi = retrofit.create(GetShowApi::class.java)
    val setShowApi = retrofit.create(SetShowApi::class.java)


    override fun getNewShow(): Single<List<Show>> =
            showApi.getShow()
                    .map { it.map(::Show) }


    override fun setShow(user: User, show: Show): Single<List<Show>> = setShowApi.setShow(
            studentID = user.id,
            password = user.password,
            showID = show.id.toString(),
            showAvatar = show.avatarPath,
            showName = show.name,
            category = show.category,
            showTime = show.showTime,
            expiredTime = show.expiredTime,
            expiredFetchTime = show.expiredFetchTime,
            descriptionURL = show.descriptionPath,
            isOnSale = show.is_OnSale.toInt(),
            isRestricted = show.isRestricted.toInt(),
            restrictionNum = show.restrictionNum

    ).map { it.map(::Show) }
}