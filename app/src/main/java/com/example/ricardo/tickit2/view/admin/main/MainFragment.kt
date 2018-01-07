package com.example.ricardo.tickit2.view.admin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BaseFragment
import com.example.ricardo.tickit2.view.fragment.home.HomeFragment
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by Ricardo on 2018/1/6.
 */

class MainFragment: BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_admin_main, null)


        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun instance(): MainFragment {
            return MainFragment()
        }
    }

}