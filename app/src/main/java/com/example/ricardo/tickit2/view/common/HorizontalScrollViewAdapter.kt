package com.example.ricardo.tickit2.view.common

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.example.ricardo.tickit2.data.model.Show
import android.widget.TextView
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.fragment.home.ShowItemAdapter
import kotlinx.android.synthetic.main.item_horizan_list.view.*


class HorizontalScrollViewAdapter(
        internal var context: Context,
        internal var layoutId: Int,
        private val list: List<ShowItemAdapter>) : ArrayAdapter<ShowItemAdapter>(context, R.layout.item_horizan_list,list) {

    private var holder: Holder = Holder()
    var view: View = View(context)
    var currentPosition = 0

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): ShowItemAdapter? {
        return list[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout: View

        if (convertView == null) {

            layout = View.inflate(context, layoutId, null)
            holder = Holder()
            holder.title = layout.findViewById<TextView>(R.id.home_film_title)
            holder.image = layout.findViewById<com.facebook.drawee.view.SimpleDraweeView>(R.id.home_film_icon)
            layout.tag = holder

        } else {
            layout = convertView
            view = layout
            holder = layout.tag as Holder
        }
        val newsSource: ShowItemAdapter = getItem(position)!!

        holder.title!!.text = newsSource.show.name
        holder.image!!.setImageURI(newsSource.show.avatarPath)

        layout.setOnClickListener { newsSource.clicked(newsSource.show) }

        return layout
    }

    private inner class Holder {
        var title: TextView? = null
        var image: com.facebook.drawee.view.SimpleDraweeView? = null
    }


}