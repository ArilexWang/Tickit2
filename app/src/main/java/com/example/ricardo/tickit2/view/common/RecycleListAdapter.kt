package com.example.ricardo.tickit2.view.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Ricardo on 2017/11/16.
 */
open class RecycleListAdapter(
        var items:List<AnyItemAdapter> = listOf()
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override final fun getItemCount() = items.size
    override final fun getItemViewType(position: Int) = items[position].layoutId
    override final fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): RecyclerView.ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return items.first{ it.layoutId == layoutId }.onCreateViewHolder(itemView)
    }

    override final fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].bindViewHolder(holder)
    }
}

typealias AnyItemAdapter=ItemAdapter
<out RecyclerView.ViewHolder>
