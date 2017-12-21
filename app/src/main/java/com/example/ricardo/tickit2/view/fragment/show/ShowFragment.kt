package com.example.ricardo.tickit2.view.fragment.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.fragment.cart.CartFragment

/**
 * Created by Ricardo on 2017/12/21.
 */
class ShowFragment: android.support.v4.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_cart, null)
    }

    companion object {
        fun instance(): ShowFragment {
            return ShowFragment()
        }
    }
}