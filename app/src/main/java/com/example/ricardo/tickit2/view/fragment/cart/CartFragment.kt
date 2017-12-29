package com.example.ricardo.tickit2.view.fragment.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R

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