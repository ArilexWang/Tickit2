package com.example.ricardo.tickit2.view.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.entity.GDUser
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.setting.SettingPresenter
import kotlinx.android.synthetic.main.activity_profile_detail.*

/**
 * Created by yuhanyin on 2017/12/13.
 */
class ProfileInfoActivity: BaseActivity(),ProfileInfoView{
   override val presenter by lazy { ProfileInfoPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)
        initView()

        val userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = userDao



    }

    private fun initView(){


    }
    companion object {

        val PROFILE_RESULT_CODE = 30001
    }
}
