package com.example.ricardo.tickit2.view.profile

import android.content.Context
import android.widget.TextView
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import android.widget.ImageView


/**
 * Created by yuhanyin on 1/8/18.
 */
class SimpleAdapter(context: Context, private val isGrid: Boolean) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return 6
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

//    fun getView(position: Int, convertView: View, parent: ViewGroup): View {
//        val viewHolder: ViewHolder
//        var view: View? = convertView
//
//        if (view == null) {
//            if (isGrid) {
//                view = layoutInflater.inflate(R.layout.simple_grid_item, parent, false)
//            } else {
//                view = layoutInflater.inflate(R.layout.simple_list_item, parent, false)
//            }
//
//            viewHolder = ViewHolder()
//            viewHolder.textView = view!!.findViewById(R.id.text_view)
//            viewHolder.imageView = view!!.findViewById(R.id.image_view) as ImageView
//            view!!.setTag(viewHolder)
//        } else {
//            viewHolder = view!!.getTag()
//        }
//
//        val context = parent.context
//        when (position) {
//            0 -> {
//                viewHolder.textView!!.text = context.getString(R.string.google_plus_title)
//                viewHolder.imageView!!.setImageResource(R.drawable.ic_google_plus_icon)
//            }
//            1 -> {
//                viewHolder.textView!!.text = context.getString(R.string.google_maps_title)
//                viewHolder.imageView!!.setImageResource(R.drawable.ic_google_maps_icon)
//            }
//            else -> {
//                viewHolder.textView!!.text = context.getString(R.string.google_messenger_title)
//                viewHolder.imageView!!.setImageResource(R.drawable.ic_google_messenger_icon)
//            }
//        }
//
//        return view
//    }

    internal class ViewHolder {
        var textView: TextView? = null
        var imageView: ImageView? = null
    }
}
