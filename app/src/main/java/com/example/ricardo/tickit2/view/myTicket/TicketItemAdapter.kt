package com.example.ricardo.tickit2.view.myTicket

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.extensions.explainOrderStatu
import com.example.ricardo.tickit2.extensions.setTime
import com.example.ricardo.tickit2.view.common.ItemAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ricardo on 2017/12/29.
 */
class TicketItemAdapter(val ticket: Ticket,val clicked: (Ticket) -> Unit): ItemAdapter<TicketItemAdapter.ViewHolder>(R.layout.item_ticket){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {
        ticketView.setImageURI(ticket.showAvatar)

        ticketView.setOnClickListener{ clicked(ticket) }

        showName.text = ticket.showName

        val date = setTime(ticket.createTime)

        createTime.text = date

        orderID.setText(ticket.id)

        studentID.setText(ticket.studentID)

        orderStatu.setText(explainOrderStatu(ticket.status))

    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val ticketView by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.ticketImage)
        val showName by bindView<TextView>(R.id.showName)
        val createTime by bindView<TextView>(R.id.createTime)
        val orderID by bindView<TextView>(R.id.orderid)
        val studentID by bindView<TextView>(R.id.order_studentid)
        val orderStatu by bindView<TextView>(R.id.order_statu)
    }






}