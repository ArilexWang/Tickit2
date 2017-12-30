package com.example.ricardo.tickit2.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.view.advertisement.AdvertisementActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.youth.banner.BannerConfig
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_horizan_list.view.*
import java.util.ArrayList


class HomeFragment: android.support.v4.app.Fragment(),HomeView,OnBannerListener {

    val presenter: HomePresenter = HomePresenter(this, BannerPicRepository.get(), ShowRepository.get())

    var mInflate:LayoutInflater? = null

    var lLayout:LinearLayout? = null
    var lLayout2:LinearLayout? = null

    private val images = ArrayList<String>()

    private val bannerPics = ArrayList<BannerPicture>()

    private val showDescription = ArrayList<String>()

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Fresco.initialize(context)
        val view = inflater!!.inflate(R.layout.fragment_home, null)
        mInflate = LayoutInflater.from(context)
        lLayout= view.findViewById(R.id.homeListHorizon);
        lLayout2= view.findViewById(R.id.homeListHorizon2);
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.start()

    }


    //banner图片点击事件
    override fun OnBannerClick(position: Int) {

        val url = bannerPics[position].targetPath
        val intent = Intent(context, AdvertisementActivity::class.java)
        intent.putExtra("url", url)
        startActivityForResult(intent, 0)

    }

    //加载banner成功
    override fun onSuccess(items: List<BannerPicture>) {
        for (item in items){
            images.add(item.picPath)
            bannerPics.add(item)
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

    //加载票务新奇日成功
    override fun onShowSuccess(items: List<Show>) {

        for (item in items){
            val view:View = mInflate!!.inflate(R.layout.item_horizan_list,homeListHorizon,false)

            view.home_film_icon.setImageURI(item.avatarPath)

            view.home_film_title.setText(item.name)

            view.setOnClickListener { viewClick(item.descriptionPath,item.category.toString(),item.id.toString()) }

            showDescription.add(item.descriptionPath)

            lLayout!!.addView(view)
        }
    }


    override fun onShow2Success(items: List<Show>) {
        for (item in items){
            val view:View = mInflate!!.inflate(R.layout.item_horizan_list,homeListHorizon2,false)

            view.home_film_icon.setImageURI(item.avatarPath)

            view.home_film_title.setText(item.name)

            view.setOnClickListener { viewClick(item.descriptionPath,item.category.toString(),item.id.toString()) }

            showDescription.add(item.descriptionPath)

            lLayout2!!.addView(view)
        }
    }

    override fun onShow2Error(error: Throwable) {
        println(error)
        Toast.makeText(context,"加载图片失败",Toast.LENGTH_SHORT).show();

    }

    //票务新奇日点击事件
    fun viewClick(path: String,category: String, id:String){
        val intent = Intent(context, AdvertisementActivity::class.java)
        intent.putExtra("url", path)
        intent.putExtra("category",category)
        intent.putExtra("id",id)
        startActivityForResult(intent, 0)
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