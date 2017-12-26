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
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.view.advertisement.AdvertisementActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.google.gson.Gson
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.activity_setting.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_horizan_list.*
import kotlinx.android.synthetic.main.item_horizan_list.view.*
import java.util.ArrayList




class HomeFragment: android.support.v4.app.Fragment(),HomeView,OnBannerListener {

    val presenter: HomePresenter = HomePresenter(this, BannerPicRepository.get())

    val imgIDs = ArrayList<Int>()

    var mInflate:LayoutInflater? = null


    private val images = ArrayList<String>()

    private val bannerPics = ArrayList<BannerPicture>()

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Fresco.initialize(context)
        val view = inflater!!.inflate(R.layout.fragment_home, null)
        mInflate = LayoutInflater.from(context)
        val lLayout:LinearLayout = view.findViewById(R.id.homeListHorizon);

        initView(lLayout)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.start()

    }


    private fun initView(layout: LinearLayout){
        imgIDs.add(R.drawable.shake)
        imgIDs.add(R.drawable.shake)

        val view:View = mInflate!!.inflate(R.layout.item_horizan_list,homeListHorizon,false)

        view.home_film_icon.setImageURI("http://p04jew294.bkt.clouddn.com/avatar.jpg")

        view.home_film_title.setText("title")

        view.home_film_grade.setText("6.0")

        layout.addView(view)


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
         Toast.makeText(context,"加载图片失败",Toast.LENGTH_SHORT).show();
     }


    companion object {

        fun instance(): HomeFragment {
            return HomeFragment()
        }
    }


}