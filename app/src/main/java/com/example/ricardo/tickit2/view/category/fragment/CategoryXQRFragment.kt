package com.example.ricardo.tickit2.view.category.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.common.RecyclerViewAdapter
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import java.util.ArrayList

/**
 * Created by yuhanyin on 1/10/18.
 */
class CategoryXQRFragment : Fragment() {

//    @BindView(R.id.recyclerView)
//    internal var mRecyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        ButterKnife.bind(this, view)

        val items = ArrayList<Any>()

        for (i in 0 until ITEM_COUNT) {
            items.add(Any())
        }


        //setup materialviewpager

        if (GRID_LAYOUT) {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
        recyclerView.setHasFixedSize(true)

        //Use this now
        recyclerView.addItemDecoration(MaterialViewPagerHeaderDecorator())
        recyclerView.adapter = RecyclerViewAdapter(items)
    }

    companion object {

        private val GRID_LAYOUT = false
        private val ITEM_COUNT = 100

        fun newInstance(): CategoryShowFragment {
            return CategoryShowFragment()
        }
    }
}
