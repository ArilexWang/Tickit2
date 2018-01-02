package com.example.ricardo.tickit2.view.advertisement

import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/12/30.
 */
interface AdvertisementView{
    fun onSuccess(items: List<Order>)

    fun onError(error: Throwable)
}