package com.example.ricardo.tickit2.view.myTicket

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.entity.Ticket
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.AnyItemAdapter
import com.example.ricardo.tickit2.view.common.ItemAdapter
import com.example.ricardo.tickit2.view.common.RecycleListAdapter

/**
 * Created by Ricardo on 2017/12/29.
 */

class MyTicketListAdapter(items: List<AnyItemAdapter>): RecycleListAdapter(items){
    fun add(itemAdapter: AnyItemAdapter){
        items+=itemAdapter
        val index=items.indexOf(itemAdapter)
        if (index==-1) return
        notifyItemInserted(index)
    }
    fun delete(itemAdapter: AnyItemAdapter){
        val index=items.indexOf(itemAdapter)
        if (index==-1) return
        items-=itemAdapter
        notifyItemRemoved(index)
    }
}
