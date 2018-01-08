package com.example.ricardo.tickit2.view.admin.set

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.extensions.bindToSwipeRefresh
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.extensions.toast
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.detail.SetDetailActivity
import com.example.ricardo.tickit2.view.admin.main.AdminMainActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_banner.*


/**
 * Created by Ricardo on 2018/1/6.
 */

class SetActivity :BaseActivity(), SetView {
    override val presenter by lazy { SetPresenter(this, BannerPicRepository.get(), ShowRepository.get()) }
    override var refresh by bindToSwipeRefresh(R.id.bannerSwipeRefreshView)

    val from by lazy { intent.getStringExtra(INTENT) }

    var userDao:GDUserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_banner)
        bannerRecyclerView.layoutManager = GridLayoutManager(this,1)

        userDao = loadDaoSession().gdUserDao
        presenter.mUserDao = userDao

        presenter.from = from

        if (from == "MAIN_SHOW"){
            toolbarTitle.setText("Show")
        }

        bannerSwipeRefreshView.setOnRefreshListener { presenter.onRefresh() }

        presenter.start()

        bannerAdd.setOnClickListener { addBtnClick() }

        bannerBack.setOnClickListener{ backBtnClick() }


    }


    fun addBtnClick(){
        val banner = BannerPicture("","","",true)
        SetDetailActivity.startFromAdd(this,banner,"ADD")

    }

    fun createCategoryItemAdapter(banner : BannerPicture) = BannerItemAdapter(banner, {bannerPicClick(banner)})

    fun createCategoryItemAdapter(show: Show) = ShowItemAdapte(show,{showClick(show)})

    fun bannerPicClick(banner: BannerPicture){

        BottomSheet.Builder(this@SetActivity).sheet(R.menu.set_banner_list).listener { dialog, which ->
            when (which) {
                R.id.set_banner -> {
                    SetDetailActivity.start(this,banner,"BANNER")
                }
                R.id.delete_banner -> {
                    presenter.deleteBanner(banner)
                }
            }
        }.build().show()

    }

    fun showClick(show: Show){
        BottomSheet.Builder(this@SetActivity).sheet(R.menu.set_banner_list).listener { dialog, which ->
            when (which) {
                R.id.set_banner -> {
                    SetDetailActivity.startFromShow(this,show,"SHOW")
                }
                R.id.delete_banner -> {

                }
            }
        }.build().show()
    }

    fun backBtnClick(){
        AdminMainActivity.start(this)
    }


    override fun onSuccess(items: List<BannerPicture>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        bannerRecyclerView.adapter = SetListAdapter(categoryItemAdapters)
    }

    override fun onError(error: Throwable) {
        println(error)
    }

    override fun deleteSuccess(success: String) {
        presenter.onRefresh()
    }

    override fun deleteError(error: Throwable) {
        this.toast(error.toString())
    }


    override fun onShowSuccess(items: List<Show>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        bannerRecyclerView.adapter = SetListAdapter(categoryItemAdapters)
    }

    override fun onShowError(error: Throwable) {
    }


    companion object {
        private const val BANNER_ARG = "Banner_Key"
        private const val INTENT = "Intent_From"

        fun getIntent(context: Context,from: String) = context.getIntent<SetActivity>()
                .apply {
                    putExtra(SetActivity.INTENT,from)
                }

        fun start(context: Context,from: String){
            val intent = getIntent(context,from)
            context.startActivity(intent)
        }




    }


}