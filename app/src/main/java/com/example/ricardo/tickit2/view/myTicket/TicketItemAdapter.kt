package com.example.ricardo.tickit2.view.myTicket

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.ItemAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ricardo on 2017/12/29.
 */
class TicketItemAdapter(val ticket: Ticket): ItemAdapter<TicketItemAdapter.ViewHolder>(R.layout.item_ticket){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {
        ticketView.setImageURI(ticket.showAvatar)

        showName.text = ticket.showName

        val date = setTime(ticket.createTime)

        createTime.text = date

    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val ticketView by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.ticket_image)
        val showName by bindView<TextView>(R.id.showName)
        val createTime by bindView<TextView>(R.id.createTime)
    }

    fun setTime(strDate: String): String{
        val dateFormate1: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmm")
        val d1: Date = dateFormate1.parse(strDate)
        val dateFormate2: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val str1 = dateFormate2.format(d1)
        return str1


    }




}