package com.example.ricardo.tickit2.view.fragment.home

import android.os.Bundle
import android.support.annotation.Nullable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.ricardo.tickit2.R


/**
 * Created by yuhanyin on 2017/12/8.
 */
 class HomeFragment: android.support.v4.app.Fragment() {

    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_home, null)
        //initHome()
        return view
    }

    private fun initHome() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {

        fun instance(): HomeFragment {
            return HomeFragment()
        }
    }
}