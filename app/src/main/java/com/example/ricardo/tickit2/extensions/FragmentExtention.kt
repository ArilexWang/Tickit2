package com.example.ricardo.tickit2.extensions

import com.example.ricardo.tickit2.base.BaseFragment
import com.example.ricardo.tickit2.greendao.gen.GDUserDao

/**
 * Created by Ricardo on 2018/1/4.
 */
//
fun BaseFragment.isAdmin(userDao: GDUserDao): Boolean{
    val db = userDao!!.queryBuilder()
    val list = db.list()
    if (list.isEmpty()) return false
    else{
        if (list[0].isAdmin) return true
        else return false
    }
}