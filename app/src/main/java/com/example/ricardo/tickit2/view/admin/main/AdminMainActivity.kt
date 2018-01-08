package com.example.ricardo.tickit2.view.admin.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.view.admin.set.SetActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.signin.SignInActivity
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

        admin_orderBtn.setOnClickListener{ orderBtnClick() }

        admin_exitBtn.setOnClickListener { exit() }


    }

    fun bannerBtnClick(){
        SetActivity.start(this, BANNER_INTENT)
    }

    fun showBtnClick(){
        SetActivity.start(this, SHOW_INTENT)
    }

    fun orderBtnClick(){
        SetActivity.start(this, ORDER_INTENT)
    }


    fun exit(){
        val intent = Intent()
        intent.setClass(this@AdminMainActivity, SignInActivity::class.java)
        startActivity(intent)
    }


    companion object {
        const val BANNER_INTENT = "MAIN_BANNER"
        const val SHOW_INTENT = "MAIN_SHOW"
        const val ORDER_INTENT = "MAIN_ORDER"

        fun getIntent(context: Context) = context
                .getIntent<AdminMainActivity>()

        fun start(context: Context){
            val intent = AdminMainActivity.getIntent(context)
            context.startActivity(intent)
        }


    }

}