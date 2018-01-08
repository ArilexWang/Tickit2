package com.example.ricardo.tickit2.view.fragment.show

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.ItemAdapter

/**
 * Created by yuhanyin on 1/8/18.
 */
class ShowItemAdapter (val show: Show, val clicked: (Show) -> Unit): ItemAdapter<ShowItemAdapter.ViewHolder>(R.layout.item_show_fragment){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {

        showPic.setImageURI(show.avatarPath)
        showName.text = show.name
        valTime.text = show.expiredTime
//        showPrice.text


    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
//        val ticketView by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.ticket_image)
//        val showName by bindView<TextView>(R.id.showName)
//        val createTime by bindView<TextView>(R.id.createTime)
//        val orderID by bindView<TextView>(R.id.orderid)
        val showPic by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.showImage)
        val showName by bindView<TextView>(R.id.showFraName)
        val valTime by bindView<TextView>(R.id.validTime)
        val showPrice by bindView<TextView>(R.id.showFraPrice)
    }

}