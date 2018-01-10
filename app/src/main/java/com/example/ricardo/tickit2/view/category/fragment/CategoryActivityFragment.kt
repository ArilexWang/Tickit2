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
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.view.admin.set.SetListAdapter
import com.example.ricardo.tickit2.view.category.CategoryItemAdapte
import com.example.ricardo.tickit2.view.category.CategoryPresenter
import com.example.ricardo.tickit2.view.category.CategoryView
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator
import kotlinx.android.synthetic.main.fragment_recyclerview.*

/**
 * Created by yuhanyin on 1/10/18.
 */
class CategoryActivityFragment : Fragment(), CategoryView {

    val presenter: CategoryPresenter by lazy { CategoryPresenter(ShowRepository.get(), this) }

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

        recyclerView.addItemDecoration(MaterialViewPagerHeaderDecorator())
        val categoryItemAdapters = showList.map(this::createCategoryItemAdapter)
        recyclerView.adapter = SetListAdapter(categoryItemAdapters)

    }

    fun createCategoryItemAdapter(show: Show) = CategoryItemAdapte(show,{})

    override fun onShowSuccess(items: List<Show>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        recyclerView.adapter = SetListAdapter(categoryItemAdapters)
    }

    override fun onShowError(error: Throwable) {

    }


    companion object {

        private val GRID_LAYOUT = false
        private val ITEM_COUNT = 100

        var showList: MutableList<Show> = mutableListOf()

        fun newInstance(showList: MutableList<Show>): CategoryActivityFragment {
            this.showList = showList
            return CategoryActivityFragment()

        }
    }
}
