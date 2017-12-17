package com.example.ricardo.tickit2.view.profile

import com.example.ricardo.tickit2.data.entity.GDUser
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import kotlinx.android.synthetic.main.activity_signin.*

/**
 * Created by Ricardo on 2017/12/17.
 */

class ProfileInfoPresenter: ProfileInfoContract.Presenter{
    var mUserDao:GDUserDao? = null

    override fun start() {


    }

    override fun getLocalAvatar(): String? {
        val db = mUserDao!!.queryBuilder()

        val list = db.list()

        if (!list.isEmpty()){

            return list[0].avatar

        }

        return null
    }

    override fun onViewDestroyed() {

    }
}