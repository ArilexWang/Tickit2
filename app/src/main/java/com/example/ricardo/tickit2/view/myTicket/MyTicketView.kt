package com.example.ricardo.tickit2.view.myTicket

import com.example.ricardo.tickit2.data.entity.Ticket
import com.example.ricardo.tickit2.data.model.Order

/**
 * Created by Ricardo on 2017/12/29.
 */

interface MyTicketView{

    fun show(items: List<Order>)
    fun showError(error: Throwable)

}