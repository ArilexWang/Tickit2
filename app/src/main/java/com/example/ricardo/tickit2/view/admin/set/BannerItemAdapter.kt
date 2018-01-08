package com.example.ricardo.tickit2.view.admin.set

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.extensions.bindView
import com.example.ricardo.tickit2.view.common.ItemAdapter

/**
 * Created by Ricardo on 2018/1/6.
 */

class BannerItemAdapter(val banner: BannerPicture, val clicked: (BannerPicture) -> Unit): ItemAdapter<BannerItemAdapter.ViewHolder>(R.layout.item_banner){

    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)
    override fun ViewHolder.onBindViewHolder() {
        bannerPic.setImageURI(banner.picPath)
        bannerPic.setOnClickListener { clicked(banner) }
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val bannerPic by bindView<com.facebook.drawee.view.SimpleDraweeView>(R.id.banner_pic)
    }





}