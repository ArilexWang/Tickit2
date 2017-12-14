package com.example.ricardo.tickit2.view.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.ItemAdapter

/**
 * Created by Ricardo on 2017/11/16.
 */

class TermAdapter(
        val content: String,
        val clicked: (String) -> Unit
): ItemAdapter<TermAdapter.ViewHolder>(R.layout.item_term){
    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder(){
        textView.text = content
        itemView.setOnClickListener{ clicked(content) }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView by bindView<TextView>(R.id.textView)
    }
}