package com.example.ricardo.tickit2.view.admin.bannerSetting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.extensions.extra
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.banner.BannerActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.setting.SettingActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_banner_setting.*
import kotlinx.android.synthetic.main.item_banner.*

/**
 * Created by Ricardo on 2018/1/6.
 */

class BannerSettingActivity:BaseActivity(),BannerSettingView{
    override val presenter by lazy{ BannerSettingPresenter(this, BannerPicRepository.get()) }

    val banner: BannerPicture by extra(BANNER_ARG)

    val from: String by lazy { intent.getStringExtra(INTENT) }

    var userDao: GDUserDao? = null
    var newBanner: BannerPicture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_setting)

        Fresco.initialize(this)

        set_banner_pic.setImageURI(banner.picPath)
        set_descriptionURL.setText(banner.targetPath)

        set_banner_pic.setOnClickListener { picClick() }


        bannerBack.setOnClickListener { backClick() }


        if(from != "ADD"){
            set_banner_save.setOnClickListener{ savaBtnClick() }
        } else{
            set_banner_save.setOnClickListener { addBtnClick() }
        }



        userDao = loadDaoSession().gdUserDao

        newBanner = BannerPicture(banner)


        switch_view.isChecked = newBanner!!.isOnDisplay

        switch_view.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {  buttonView, isChecked ->
            if (isChecked) {
                newBanner!!.isOnDisplay = isChecked
            } else {
                newBanner!!.isOnDisplay = isChecked
            }
        })

        presenter.mUserDao = userDao


    }

    fun backClick(){
        BannerActivity.start(this)
    }

    override fun onSuccess(items: List<BannerPicture>) {
        BannerActivity.start(this)
    }

    override fun onError(error: Throwable) {
        println(error)
    }


    override fun createSuccess(items: List<BannerPicture>) {
        BannerActivity.start(this)
    }

    override fun createFaile(error: Throwable) {
        println(error)
    }

    fun savaBtnClick(){
        newBanner!!.targetPath = set_descriptionURL.text.toString()
        presenter.setBanner(newBanner!!)
    }

    fun addBtnClick(){
        newBanner!!.targetPath = set_descriptionURL.text.toString()
        presenter.createBanner(newBanner!!)
    }



    fun picClick(){
        BottomSheet.Builder(this@BannerSettingActivity).sheet(R.menu.list).listener { dialog, which ->
            when (which) {
                R.id.takePhoto -> {
                    val intent = Intent()
                    intent.setClass(this@BannerSettingActivity, SettingActivity::class.java)
                    startActivity(intent)
                }
                R.id.choosePhoto -> {
                    newBanner!!.targetPath = set_descriptionURL.text.toString()
                    PhotoChoseActivity.startFromBanner(this,"banner",newBanner!!)

                }
            }
        }.build().show()
    }

    companion object {
        private const val BANNER_ARG = "Banner_Key"
        private const val INTENT = "INTENT_FROM"

        fun getIntent(context: Context, banner: BannerPicture) = context
                .getIntent<BannerSettingActivity>()
                .apply { putExtra(BANNER_ARG, banner) }

        fun getIntent(context: Context, banner: BannerPicture, from: String) = context
                .getIntent<BannerSettingActivity>()
                .apply {
                    putExtra(BANNER_ARG,banner)
                    putExtra(INTENT,from)
                }

        fun start(context: Context, banner: BannerPicture ){
            val intent = getIntent(context, banner)
            context.startActivity(intent)
        }

        fun startFromAdd(context: Context,banner: BannerPicture,from: String){
            val intent = getIntent(context,banner,from)
            context.startActivity(intent)
        }

    }



}