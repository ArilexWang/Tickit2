package com.example.ricardo.tickit2.extensions

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.ricardo.tickit2.data.*
import java.text.SimpleDateFormat
import java.util.*
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

fun setTime(strDate: String): String{
    val dateFormate1: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmm")
    val d1: Date = dateFormate1.parse(strDate)
    val dateFormate2: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    val str1 = dateFormate2.format(d1)
    return str1
}

fun getTimeString(strDate: String): String{
    val dateFormate1: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val d1: Date = dateFormate1.parse(strDate)
    val dateFormate2: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmm")
    val str1 = dateFormate2.format(d1)
    return str1
}

fun explainOrderStatu(statu: Int) :String?{
    if (statu == ORDER_OPEN_UNFETCHED){
        return ORDER_OPEN_UNFETCHED_MESSAGE
    } else if(statu == ORDER_SUCCESS){
        return ORDER_SUCCESS_MESSAGE
    } else if(statu == ORDER_CANCELED_BY_USER){
        return ORDER_CANCELED_BY_USER_MESSAGE
    } else if(statu == ORDER_EXPIRED_UNFETCHED){
        return ORDER_EXPIRED_UNFETCHED_MESSAGE
    }
    return null
}