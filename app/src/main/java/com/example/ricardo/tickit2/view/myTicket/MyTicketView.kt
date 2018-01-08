package com.example.ricardo.tickit2.view.myTicket

import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.model.Ticket

/**
 * Created by Ricardo on 2017/12/29.
 */

interface MyTicketView{
    var refresh: Boolean
    fun show(items: List<Ticket>)
    fun showError(error: Throwable)

}