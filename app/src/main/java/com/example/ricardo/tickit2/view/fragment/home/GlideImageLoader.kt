package com.example.ricardo.tickit2.view.fragment.home

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader
import android.net.Uri
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.facebook.drawee.view.SimpleDraweeView




/**
 * Created by yuhanyin on 12/19/17.
 */
class GlideImageLoader:ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        Glide.with(context).load(path).into(imageView)

        //Picasso 加载图片简单用法
        //Picasso.with(context).load(path).into(imageView)

        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        //val uri = Uri.parse(path as String)
        //imageView!!.setImageURI(uri)
    }

//    override fun createImageView(context: Context?): ImageView {
//        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//        return SimpleDraweeView(context)
//    }
}