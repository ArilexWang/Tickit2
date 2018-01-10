package com.example.ricardo.tickit2.view.fragment.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.App
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.fragment.profile.functions.SystemActivity
import com.example.ricardo.tickit2.view.myTicket.MyTickeyActivity
import com.example.ricardo.tickit2.view.profile.ProfileInfoActivity
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

        val  userDao = App.instance.getDaoSession().gdUserDao

        val database = userDao.queryBuilder()

        val list = database.list()

        val avatarPath = list[0].avatar

        me_login_iv_head.setImageURI(avatarPath)
        me_login_tv_username.setText(list[0].nickName)
        println(list[0].vipPoint)
        me_login_tv_balance.setText(list[0].vipPoint.toString())

        imgProfileDetail.setOnClickListener {
            val intent = Intent()
            intent.setClass(this.context, ProfileInfoActivity::class.java)
            startActivityForResult(intent,0)
        }

        myTicketBtn.setOnClickListener {
            val intent = Intent()
            intent.setClass(this.context, MyTickeyActivity::class.java)
            startActivityForResult(intent,1)
        }
        systemService.setOnClickListener {
            val intent = Intent()
            intent.setClass(this.context, SystemActivity::class.java)
            startActivityForResult(intent,2)

        }
        contactTele.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + contactTele.text)
            startActivity(intent)
        }


    }
    companion object {

        fun instance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}