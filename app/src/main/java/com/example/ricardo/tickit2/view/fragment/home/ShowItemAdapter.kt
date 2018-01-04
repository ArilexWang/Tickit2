package com.example.ricardo.tickit2.view.fragment.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.HorizontalItemAdapter
import com.example.ricardo.tickit2.view.common.ItemAdapter
import com.example.ricardo.tickit2.view.myTicket.TicketItemAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ricardo on 2018/1/4.
 */
class ShowItemAdapter(val show: Show, val clicked: (Show) -> Unit): HorizontalItemAdapter<ShowItemAdapter.ViewHolder>(R.layout.item_horizan_list){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {
        showView.setImageURI(show.avatarPath)
        showView.setOnClickListener{ clicked(show) }
        showName.text = show.name
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val showView by  lazy {itemView.findViewById<com.facebook.drawee.view.SimpleDraweeView>(R.id.home_film_icon)}

        val showName by lazy {itemView.findViewById<TextView>(R.id.home_film_title)  }

    }


}