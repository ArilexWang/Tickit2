package com.example.ricardo.tickit2.view.admin.main

import android.content.Intent
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.view.admin.banner.BannerActivity
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

        admin_exitBtn.setOnClickListener { exit() }

    }

    fun bannerBtnClick(){
        val intent = Intent()
        intent.setClass(this@AdminMainActivity, BannerActivity::class.java)
        startActivity(intent)
    }


    fun exit(){
        val intent = Intent()
        intent.setClass(this@AdminMainActivity, SignInActivity::class.java)
        startActivity(intent)
    }

}