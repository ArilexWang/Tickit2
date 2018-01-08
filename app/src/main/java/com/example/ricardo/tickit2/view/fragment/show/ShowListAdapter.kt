package com.example.ricardo.tickit2.view.fragment.show

import com.example.ricardo.tickit2.view.common.AnyItemAdapter
import com.example.ricardo.tickit2.view.common.RecycleListAdapter

/**
 * Created by yuhanyin on 1/8/18.
 */

class ShowListAdapter(items: List<AnyItemAdapter>): RecycleListAdapter(items){
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