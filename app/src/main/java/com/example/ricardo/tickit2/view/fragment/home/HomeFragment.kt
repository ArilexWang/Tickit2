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
import com.example.ricardo.tickit2.R.id.image
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.view.advertisement.AdvertisementActivity
import com.facebook.drawee.view.SimpleDraweeView
import com.google.gson.Gson
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList


class HomeFragment: android.support.v4.app.Fragment(),HomeView,OnBannerListener {

    val presenter: HomePresenter = HomePresenter(this, BannerPicRepository.get())

    private val images = ArrayList<String>()

    private val bannerPics = ArrayList<BannerPicture>()

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_home, null)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.start()
    }

    override fun OnBannerClick(position: Int) {
        println(bannerPics[position].targetPath)
        val url = bannerPics[position].targetPath
        val intent = Intent(context, AdvertisementActivity::class.java)
        intent.putExtra("url", url)
        startActivityForResult(intent, 0)

    }

     override fun onSuccess(items: List<BannerPicture>) {
         for (item in items){
             images.add(item.picPath)
             bannerPics.add(item)
         }
         banner.setImageLoader(GlideImageLoader())
         banner.setOnBannerListener(this)
         //设置图片集合
         banner.setImages(images)
         banner.setIndicatorGravity(BannerConfig.CENTER)
         //banner设置方法全部调用完毕时最后调用
         banner.start()
     }


     override fun onError(error: Throwable) {

     }


    companion object {

        fun instance(): HomeFragment {
            return HomeFragment()
        }
    }


}