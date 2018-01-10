package com.example.ricardo.tickit2.view.fragment.cart

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.common.RecyclerViewAdapter
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import java.util.ArrayList

class CartFragment: android.support.v4.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_cart, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = ArrayList<Any>()

        for (i in 0 until ITEM_COUNT) {
            items.add(Any())
        }


        //setup materialviewpager

        if (GRID_LAYOUT) {
            recyclerCartView.layoutManager = GridLayoutManager(activity, 2)
        } else {
            recyclerCartView.layoutManager = LinearLayoutManager(activity)
        }
        recyclerCartView.setHasFixedSize(true)

        //Use this now
        recyclerCartView.addItemDecoration(MaterialViewPagerHeaderDecorator())
        //recyclerCartView.adapter = RecyclerViewAdapter(items)
    }
    companion object {

        private val GRID_LAYOUT = false
        private val ITEM_COUNT = 10

        fun instance(): CartFragment {
            return CartFragment()
        }
    }
}