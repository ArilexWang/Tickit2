package com.example.ricardo.tickit2.data.network.repository

import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.api.orderapi.CreateOrderApi
import com.example.ricardo.tickit2.data.network.api.orderapi.GetOrderApi
import com.example.ricardo.tickit2.data.network.provider.retrofit
import io.reactivex.Single

/**
 * Created by Ricardo on 2017/12/30.
 */
class OrderRepositoryImpl: OrderRepository {

    val orderApi = retrofit.create(CreateOrderApi::class.java)
    val getOrderApi = retrofit.create(GetOrderApi::class.java)

    override fun createOrder(user: User, ticketTypeID: Long): Single<List<Order>> = orderApi.createOrder(
            studentID = user.id.toString(),
            password = user.password,
            ticketTypeID = ticketTypeID
    ).map { it.map(::Order) }


    override fun getOrder(user: User): Single<List<Ticket>> = getOrderApi.getOrder(
            studentID = user.id,
            password = user.password
    ).map { it.map(::Ticket) }

}