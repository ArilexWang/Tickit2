package com.example.ricardo.tickit2.view.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.profile.ProfileInfoActivity
import com.example.ricardo.tickit2.view.setting.SettingActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment: android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Fresco.initialize(context)
        val view = inflater!!.inflate(R.layout.fragment_profile, null)

        return view
    }

    //将需要点击事件的控件放在这里监听
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imgProfileDetail.setOnClickListener {
            val intent = Intent()
            intent.setClass(this.context, ProfileInfoActivity::class.java)
            startActivity(intent)
        }
        systemService.setOnClickListener {
            val intent = Intent()
            intent.setClass(this.context, SettingActivity::class.java)
            startActivity(intent)
        }
        myTicketBtn.setOnClickListener {

            imgProfileDetail!!.setOnClickListener {
                val intent = Intent()
                intent.setClass(this.context, ProfileInfoActivity::class.java)
                startActivity(intent)
            }
        }


    }
    companion object {

        fun instance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}