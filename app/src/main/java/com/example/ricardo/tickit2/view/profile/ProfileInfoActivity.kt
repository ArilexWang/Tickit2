package com.example.ricardo.tickit2.view.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ricardo.tickit2.R

/**
 * Created by yuhanyin on 2017/12/13.
 */
class ProfileInfoActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)
        initView()

        //getSupportFragmentManager().beginTransaction().replace(R.id.common_content_layout, ProfileFragment()).commit()
    }
    private fun initView(){


    }
    companion object {

        val PROFILE_RESULT_CODE = 30001
    }
}
