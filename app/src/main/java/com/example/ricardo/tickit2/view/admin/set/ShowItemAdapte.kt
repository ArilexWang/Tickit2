package com.example.ricardo.tickit2.view.admin.set

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.ItemAdapter

/**
 * Created by Ricardo on 2018/1/8.
 */

class ShowItemAdapte(val show: Show, val clicked: (Show) -> Unit): ItemAdapter<ShowItemAdapte.ViewHolder>(R.layout.item_show){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {
        showPic.setImageURI(show.avatarPath)
        showPic.setOnClickListener { clicked(show) }
        showName.setText(show.name)
        showDescription.setText(show.descriptionPath)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val showPic by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.show_icon)
        val showName by bindView<TextView>(R.id.show_name)
        val showDescription by bindView<TextView>(R.id.show_descriptionURL)
    }





}