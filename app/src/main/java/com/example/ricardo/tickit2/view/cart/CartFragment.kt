package com.example.ricardo.tickit2.view.cart

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R

/**
 * Created by yuhanyin on 2017/12/8.
 */
class CartFragment: android.support.v4.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_cart, null)
    }

    companion object {
        fun instance(): CartFragment {
            return CartFragment()
        }
    }
}