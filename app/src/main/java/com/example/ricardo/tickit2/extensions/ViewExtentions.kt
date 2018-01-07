package com.example.ricardo.tickit2.extensions

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast

/**
 * Created by Ricardo on 2017/11/16.
 */
fun <T: View> RecyclerView.ViewHolder.bindView(viewId: Int)
        = lazy {itemView.findViewById<T>(viewId)}




fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Boolean.toInt() = if (this) 1 else 0