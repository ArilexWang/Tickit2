package com.example.ricardo.tickit2.view.fragment.show

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*

import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.entity.TicketType
import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_show.*
import android.support.v7.widget.LinearLayoutManager
import com.example.ricardo.tickit2.base.BaseFragment
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.view.category.fragment.CategoryShowFragment
import com.example.ricardo.tickit2.view.common.RecyclerViewAdapter
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator


class ShowFragment :BaseFragment(), View.OnClickListener,ShowView{

    val presenter by lazy { ShowPresenter(this, ShowRepository.get()) }

    private var suggestions = ArrayList<Show>()
    private var searchSuggestionsAdapter: SearchSuggestionsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_show, null)

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
            ticketRecycleView.layoutManager = GridLayoutManager(activity, 2)
        } else {
            ticketRecycleView.layoutManager = LinearLayoutManager(activity)
        }
        ticketRecycleView.setHasFixedSize(true)

        //Use this now
        ticketRecycleView.addItemDecoration(MaterialViewPagerHeaderDecorator())
        ticketRecycleView.adapter = RecyclerViewAdapter(items)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchSuggestionsAdapter = SearchSuggestionsAdapter(layoutInflater)

        searchBar.setMaxSuggestionCount(4)
        searchBar.setHint("find tickets...")

        presenter.start()

        searchSuggestionsAdapter!!.suggestions = suggestions
        searchBar.setCustomSuggestionAdapter(searchSuggestionsAdapter)



        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                searchSuggestionsAdapter!!.filter.filter(searchBar!!.text)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })

    }





    override fun onSuccess(items: List<Show>) {
        for (item in items){
            suggestions.add(item)
        }
    }

    override fun onError(error: Throwable) {

    }


    override fun onClick(v: View?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        suggestions.clear()
    }

    companion object {
        private val GRID_LAYOUT = false
        private val ITEM_COUNT = 100

        fun instance(): ShowFragment {
            return ShowFragment()
        }
    }

}
