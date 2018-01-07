package com.example.ricardo.tickit2.view.admin.banner

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.view.admin.bannerSetting.BannerSettingActivity
import com.example.ricardo.tickit2.view.advertisement.AdvertisementActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.myTicket.MyTicketListAdapter
import com.example.ricardo.tickit2.view.myTicket.TicketItemAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_banner.*
import kotlinx.android.synthetic.main.activity_myticket.*


/**
 * Created by Ricardo on 2018/1/6.
 */

class BannerActivity:BaseActivity(),BannerView{
    override val presenter by lazy { BannerPresenter(this, BannerPicRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_banner)
        bannerRecyclerView.layoutManager = GridLayoutManager(this,1)

        bannerSwipeRefreshView.setOnRefreshListener { presenter.onRefresh() }

        presenter.start()

        bannerAdd.setOnClickListener { addBtnClick() }

    }


    fun addBtnClick(){
        val banner = BannerPicture("","","",true)
        BannerSettingActivity.start(this,banner)
    }


    fun createCategoryItemAdapter(banner : BannerPicture) = BannerItemAdapter(banner, {bannerPicClick(banner)})


    fun bannerPicClick(banner: BannerPicture){
        BannerSettingActivity.start(this,banner)
    }

    override fun onSuccess(items: List<BannerPicture>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        bannerRecyclerView.adapter = BannerListAdapter(categoryItemAdapters)
    }

    override fun onError(error: Throwable) {
        println(error)
    }



}