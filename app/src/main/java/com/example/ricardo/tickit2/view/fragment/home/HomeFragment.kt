package com.example.ricardo.tickit2.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.ricardo.tickit2.App
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BaseFragment
import com.example.ricardo.tickit2.data.BANNER_ARG
import com.example.ricardo.tickit2.data.PWXQR_NUMBER
import com.example.ricardo.tickit2.data.SHOW_ARG
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.extensions.bindToSwipeRefresh
import com.example.ricardo.tickit2.extensions.isAdmin
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.view.advertisement.AdvertisementActivity
import com.example.ricardo.tickit2.view.common.ShowScrollViewAdapter
import com.example.ricardo.tickit2.view.common.ShowHorizontalScrollview
import com.facebook.drawee.backends.pipeline.Fresco
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList


class HomeFragment: BaseFragment(),HomeView,OnBannerListener {

    val presenter: HomePresenter = HomePresenter(this, BannerPicRepository.get(), ShowRepository.get())
    override var refresh by bindToSwipeRefresh(R.id.home_swipeRefreshView)
    var scollerView: ShowHorizontalScrollview? = null
    var scollerView2: ShowHorizontalScrollview? = null

    private val images = ArrayList<String>()

    private val bannerPics = ArrayList<BannerPicture>()


    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Fresco.initialize(context)
        val view = inflater!!.inflate(R.layout.fragment_home, null)

        scollerView = view.findViewById(R.id.pwxqrScrollView)

        scollerView2 = view.findViewById(R.id.pwtjScrollView)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.start()

        home_swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
    }

    //banner图片点击事件
    override fun OnBannerClick(position: Int) {
        val tempBanner:BannerPicture = bannerPics[position]
        AdvertisementActivity.start(context,tempBanner, BANNER_ARG)
    }

    //加载banner成功
    override fun onSuccess(items: List<BannerPicture>) {
        for (item in items){
            if (item.isOnDisplay){
                images.add(item.picPath)
                bannerPics.add(item)
            }
        }
        banner.setImageLoader(GlideImageLoader())
        banner.setOnBannerListener(this)
        banner.setDelayTime(5000)
        //设置图片集合
        banner.setImages(images)
        banner.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        banner.start()
    }

    //加载banner失败
    override fun onError(error: Throwable) {
        println(error)
        Toast.makeText(context,"加载图片失败",Toast.LENGTH_SHORT).show();
    }

    //加载票务成功
    override fun onShowSuccess(items: List<Show>) {

        var pwxqrList: MutableList<Show> = mutableListOf()
        val pwtjList: MutableList<Show> = mutableListOf()

        for (item in items){
            if (item.category == PWXQR_NUMBER){
                pwxqrList.add(item)
            }else{
                pwtjList.add(item)
            }
        }

        val categoryItemAdapters1 = pwxqrList.map(this::createCategoryItemAdapter)
        val categoryItemAdapters2 = pwtjList.map(this::createCategoryItemAdapter)

        val customListAdapter = ShowScrollViewAdapter(context,R.layout.item_horizan_list,categoryItemAdapters1)
        val customListAdapter2 = ShowScrollViewAdapter(context,R.layout.item_horizan_list,categoryItemAdapters2)

        scollerView!!.setAdapter(context,customListAdapter)
        scollerView2!!.setAdapter(context,customListAdapter2)
    }

    fun createCategoryItemAdapter(show: Show) = ShowItemAdapter(show,{viewClick(show)})

    //票务新奇日点击事件
    fun viewClick(show: Show){
        AdvertisementActivity.start(context,show, SHOW_ARG)
    }

    override fun onShowError(error: Throwable) {
        print(error)
    }

    companion object {

        fun instance(): HomeFragment {
            return HomeFragment()
        }
    }


}