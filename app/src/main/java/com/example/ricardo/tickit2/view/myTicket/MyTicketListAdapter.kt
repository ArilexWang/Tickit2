package com.example.ricardo.tickit2.view.myTicket

import com.example.ricardo.tickit2.view.common.AnyItemAdapter
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
