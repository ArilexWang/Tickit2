package com.example.ricardo.tickit2.view.myTicket

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.entity.Ticket
import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.ItemAdapter

/**
 * Created by Ricardo on 2017/12/29.
 */
class TicketItemAdapter(val ticket: Order): ItemAdapter<TicketItemAdapter.ViewHolder>(R.layout.item_ticket){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {
        ticketView.setImageURI("")
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val ticketView by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.ticket_image)
    }






}