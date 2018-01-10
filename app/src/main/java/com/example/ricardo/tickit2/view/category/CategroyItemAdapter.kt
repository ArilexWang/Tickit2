package com.example.ricardo.tickit2.view.category

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.extensions.setTime
import com.example.ricardo.tickit2.view.admin.set.ShowItemAdapte
import com.example.ricardo.tickit2.view.common.ItemAdapter

/**
 * Created by Ricardo on 2018/1/10.
 */


class CategoryItemAdapte(val show: Show, val clicked: (Show) -> Unit): ItemAdapter<CategoryItemAdapte.ViewHolder>(R.layout.list_item_card_small){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {
        showPic.setImageURI(show.avatarPath)
        showPic.setOnClickListener { clicked(show) }
        showName.setText(show.name)
        showExpiredTime.setText(setTime(show.expiredTime))
        showExpiredFetchTime.setText(setTime(show.expiredFetchTime))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val showPic by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.ticketImage)
        val showName by bindView<TextView>(R.id.showBtnName)
        val showExpiredTime by bindView<TextView>(R.id.ticketTime)
        val showExpiredFetchTime by bindView<TextView>(R.id.tickeFetchTime)
    }


}