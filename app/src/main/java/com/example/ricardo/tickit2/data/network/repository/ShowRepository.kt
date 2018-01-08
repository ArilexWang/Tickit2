package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.provider.Provider
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/26.
 */
interface ShowRepository {

    fun getNewShow(): Single<List<Show>>
    //fun setShow(user: User,show: Show): Single<List<Show>>

    companion object : Provider<ShowRepository>() {
        override fun creator() =  ShowRepositoryImpl()
    }
}