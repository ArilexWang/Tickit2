package com.example.ricardo.tickit2.view.admin.banner

import com.example.ricardo.tickit2.view.common.AnyItemAdapter
import com.example.ricardo.tickit2.view.common.RecycleListAdapter

/**
 * Created by Ricardo on 2018/1/6.
 */
class BannerListAdapter(items: List<AnyItemAdapter>): RecycleListAdapter(items){
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
