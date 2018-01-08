package com.example.ricardo.tickit2.view.admin.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.view.admin.banner.BannerActivity
import com.example.ricardo.tickit2.view.admin.bannerSetting.BannerSettingActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.signin.SignInActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_admin_main.*

/**
 * Created by Ricardo on 2018/1/6.
 */

class AdminMainActivity: BaseActivity(){
    override val presenter: BasePresenter by lazy { AdminMainPresenter() }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

        admin_bannerBtn.setOnClickListener{ bannerBtnClick() }

        admin_showBtn.setOnClickListener{ showBtnClick() }

        admin_exitBtn.setOnClickListener { exit() }

    }

    fun bannerBtnClick(){
        BannerActivity.start(this, BANNER_INTENT)
    }

    fun showBtnClick(){
        BannerActivity.start(this, SHOW_INTENT)
    }


    fun exit(){
        val intent = Intent()
        intent.setClass(this@AdminMainActivity, SignInActivity::class.java)
        startActivity(intent)
    }


    companion object {
        private const val BANNER_INTENT = "MAIN_BANNER"
        private const val SHOW_INTENT = "MAIN_SHOW"

        fun getIntent(context: Context) = context
                .getIntent<AdminMainActivity>()

        fun start(context: Context){
            val intent = AdminMainActivity.getIntent(context)
            context.startActivity(intent)
        }


    }

}