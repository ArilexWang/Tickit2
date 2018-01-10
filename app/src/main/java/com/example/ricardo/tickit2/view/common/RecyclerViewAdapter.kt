package com.example.ricardo.tickit2.view.common

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Show

/**
 * Created by yuhanyin on 1/4/18.
 */
class RecyclerViewAdapter(internal var contents: List<Show>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0 -> return TYPE_CELL
            else -> return TYPE_CELL
        }
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        var view: View? = null

        when (viewType) {
            TYPE_HEADER -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_card_big, parent, false)
                return object : RecyclerView.ViewHolder(view!!) {

                }
            }
            TYPE_CELL -> {
                view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_card_small, parent, false)
                return object : RecyclerView.ViewHolder(view!!) {

                }
            }
        }
        return null
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HEADER -> {
            }
            TYPE_CELL -> {
            }
        }
    }

    companion object {

        internal val TYPE_HEADER = 0
        internal val TYPE_CELL = 1
    }
}