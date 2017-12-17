package com.example.ricardo.tickit2.view.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_profile_detail.*

/**
 * Created by yuhanyin on 2017/12/13.
 */
class ProfileInfoActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)
        initView()
    }
    private fun initView(){
        userItemAvatar.setOnClickListener {
            val intent = Intent()
            intent.setClass(this.applicationContext, SettingActivity::class.java)
            startActivity(intent)
        }


    }
    companion object {

        val PROFILE_RESULT_CODE = 30001
    }
}
