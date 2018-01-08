package com.example.ricardo.tickit2.extensions

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Ricardo on 2017/11/16.
 */
fun <T: View> RecyclerView.ViewHolder.bindView(viewId: Int)
        = lazy {itemView.findViewById<T>(viewId)}



fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Boolean.toInt() = if (this) 1 else 0

fun Activity.bindToSwipeRefresh(@IdRes swipeRefreshLayoutId: Int): ReadWriteProperty<Any?, Boolean>
        = SwipeRefreshBinding(lazy { findViewById<SwipeRefreshLayout>(swipeRefreshLayoutId)}  )

private class SwipeRefreshBinding(lazyViewProvider: Lazy<SwipeRefreshLayout>) : ReadWriteProperty<Any?
        ,Boolean> {
    val view by lazyViewProvider
    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return view.isRefreshing
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        view.isRefreshing = value
    }
}