package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.provider.Provider
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/30.
 */
interface OrderRepository {

    fun createOrder(user: User,ticketTypeID: Long): Single<List<Order>>
    fun getOrder(user: User): Single<List<Order>>

    companion object : Provider<OrderRepository>() {
        override fun creator() =  OrderRepositoryImpl()
    }
}