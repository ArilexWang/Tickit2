package com.example.ricardo.tickit2.view.common

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Ricardo on 2017/11/16.
 */
abstract class ItemAdapter<T: RecyclerView.ViewHolder>
(@LayoutRes open val layoutId: Int){
    abstract fun onCreateViewHolder(itemView: View):T

    @Suppress("UNCHECKED_CAST")
    fun bindViewHolder(holder: RecyclerView.ViewHolder){
        (holder as T).onBindViewHolder()
    }

    abstract fun T.onBindViewHolder()
}