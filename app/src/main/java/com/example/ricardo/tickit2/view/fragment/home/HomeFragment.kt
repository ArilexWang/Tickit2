package com.example.ricardo.tickit2.view.fragment.home

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.annotation.Nullable
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.R.id.banner
import com.facebook.drawee.view.SimpleDraweeView
import com.google.gson.Gson
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList


/**
 * Created by yuhanyin on 2017/12/8.
 */
 class HomeFragment: android.support.v4.app.Fragment() {


    private val images = java.util.ArrayList<String>()

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_home, null)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initBanner()


    }

    private fun initBanner() {
        images.add("http://static.panoramio.com/photos/large/132796978.jpg")
        images.add("http://static.panoramio.com/photos/large/132796977.jpg")
        images.add("http://static.panoramio.com/photos/large/133036192.jpg")
        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        banner.setImages(images)
        banner.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        banner.start()
    }


    companion object {

        fun instance(): HomeFragment {
            return HomeFragment()
        }
    }


}