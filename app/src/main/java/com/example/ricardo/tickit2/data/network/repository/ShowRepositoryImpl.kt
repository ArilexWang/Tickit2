package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.api.GetShowApi
import com.example.ricardo.tickit2.data.network.provider.retrofit
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/26.
 */
class ShowRepositoryImpl: ShowRepository{
    val showApi = retrofit.create(GetShowApi::class.java)

    override fun getNewShow(category: Int): Single<List<Show>> =
            showApi.getShow(category)
                    .map { it.map(::Show) }
}