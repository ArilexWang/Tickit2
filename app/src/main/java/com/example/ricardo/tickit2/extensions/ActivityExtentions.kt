package com.example.ricardo.tickit2.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.example.ricardo.tickit2.App
import com.example.ricardo.tickit2.base.BaseFragment
import com.example.ricardo.tickit2.data.entity.GDUser
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.common.BaseActivity

/**
 * Created by Ricardo on 2017/11/10.
 */

fun BaseActivity.loadDaoSession() = (application as App).getDaoSession()

fun BaseActivity.isAdmin(userDao: GDUserDao): Boolean{
    val db = userDao!!.queryBuilder()
    val list = db.list()
    if (list.isEmpty()) return false
    else{
        if (list[0].isAdmin) return true
        else return false
    }
}


fun <T : Parcelable> Activity.extra(key: String, default: T? = null): Lazy<T> = lazy {
    intent?.extras?.getParcelable<T>(key) ?: default ?: throw Error("No value $key in extras") }


inline fun <reified T : Activity> Context.getIntent() = Intent(this, T::class.java)


fun BaseActivity.saveUserToLocal(item: User, userDao: GDUserDao){
    val db = userDao!!.queryBuilder()

    val list = db.list()

    //如果本地数据库为空，插入账户数据
    if (list.isEmpty() || list.size <= 0){

        val gdUser: GDUser = GDUser(item)

        userDao.insert(gdUser)

        val sUser = userDao.load(item.id)

        println(sUser.id)
    }
    //若不为空，则删掉再插入，保持数据库只存一个账户信息
    else {
        println(list[0].id)
        userDao.deleteByKey(list[0].id)

        val db = userDao!!.queryBuilder()
        val list = db.list()
        if (list.isEmpty() || list.size <= 0){

            val gdUser: GDUser = GDUser(item)
            userDao.insert(gdUser)

            val sUser = userDao.load(item.id)
            println(sUser.id)
        }


    }
}