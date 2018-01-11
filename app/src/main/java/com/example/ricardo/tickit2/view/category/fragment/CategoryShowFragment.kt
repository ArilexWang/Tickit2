package com.example.ricardo.tickit2.view.category.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.view.admin.set.SetListAdapter
import com.example.ricardo.tickit2.view.category.CategoryItemAdapte
import com.example.ricardo.tickit2.view.common.RecyclerViewAdapter
import com.example.ricardo.tickit2.view.fragment.home.HomeFragment
import com.example.ricardo.tickit2.view.fragment.home.HomePresenter
import com.example.ricardo.tickit2.view.fragment.home.HomeView
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator
import kotlinx.android.synthetic.main.fragment_recyclerview.*
import java.util.ArrayList

/**
 * Created by yuhanyin on 1/4/18.
 */
class CategoryShowFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recyclerview, container, false)
    }




    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        if (GRID_LAYOUT) {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
        recyclerView.setHasFixedSize(true)

        //Use this now
        recyclerView.addItemDecoration(MaterialViewPagerHeaderDecorator())
        val categoryItemAdapters = CategoryShowFragment.showList.map(this::createCategoryItemAdapter)
        recyclerView.adapter = SetListAdapter(categoryItemAdapters)

    }

    fun createCategoryItemAdapter(show: Show) = CategoryItemAdapte(show,{})

    companion object {

        private val GRID_LAYOUT = false
        private val ITEM_COUNT = 100

        var showList: MutableList<Show> = mutableListOf()

        fun newInstance(list: MutableList<Show>): CategoryShowFragment {
            showList = list
            return CategoryShowFragment()
        }
    }
}
