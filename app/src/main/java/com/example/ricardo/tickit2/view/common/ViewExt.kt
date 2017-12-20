package com.example.ricardo.tickit2.view.common

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by yuhanyin on 12/19/17.
 */
fun <T: View> RecyclerView.ViewHolder.bindView(viewId: Int)
        = lazy { itemView.findViewById<T>(viewId) }

fun ImageView.loadImage(photoUrl: String){
    Glide.with(context).load(photoUrl).into(this)
}

fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG){
    Toast.makeText(this,text, length).show()
}

fun Activity.bindToSwipeRefresh(@IdRes SwipeRefreshLayoutId: Int): ReadWriteProperty<Any?, Boolean>
        =SwipeRefreshBinding(lazy { findViewById<SwipeRefreshLayout>(SwipeRefreshLayoutId) })


private class SwipeRefreshBinding(lazyViewProvider: Lazy<SwipeRefreshLayout>) : ReadWriteProperty<Any?, Boolean> {
    val view by lazyViewProvider
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        view.isRefreshing = value
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean{
        return view.isRefreshing
    }
}