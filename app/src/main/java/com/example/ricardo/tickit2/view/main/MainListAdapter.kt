package com.example.ricardo.tickit2.view.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.ricardo.tickit2.view.common.AnyItemAdapter
import com.example.ricardo.tickit2.view.common.RecycleListAdapter

/**
 * Created by Ricardo on 2017/11/16.
 */
class MainListAdapter(items: List<AnyItemAdapter>): RecycleListAdapter(items){
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

