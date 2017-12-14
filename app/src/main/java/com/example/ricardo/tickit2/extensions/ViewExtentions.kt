package com.example.ricardo.tickit2.extensions

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Ricardo on 2017/11/16.
 */
fun <T: View> RecyclerView.ViewHolder.bindView(viewId: Int)
        = lazy {itemView.findViewById<T>(viewId)}