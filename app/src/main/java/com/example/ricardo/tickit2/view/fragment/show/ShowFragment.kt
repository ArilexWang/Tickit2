package com.example.ricardo.tickit2.view.fragment.show

import android.os.Bundle

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*

import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.entity.TicketType
import java.util.ArrayList

import kotlinx.android.synthetic.main.fragment_show.*
import android.support.v7.widget.LinearLayoutManager


/**
 * Created by yuhanyin on 2017/12/8.
 */
class ShowFragment :android.support.v4.app.Fragment(), View.OnClickListener{


    private var suggestions = ArrayList<TicketType>()
    private var searchSuggestionsAdapter: SearchSuggestionsAdapter? = null

    // Sample data
    private val tickets = arrayOf("天鹅湖",
            "Vincent",
            "猫Cats",
            "奇迹展",
            "芳华——舞团",
            "C4专场")


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_show, null)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchSuggestionsAdapter = SearchSuggestionsAdapter(layoutInflater)

        searchBar.setMaxSuggestionCount(2)
        searchBar.setHint("find tickets...")

        for (i in 1..6) {
            suggestions.add(TicketType(tickets[i - 1], (i * 10).toFloat()))
        }

        searchSuggestionsAdapter!!.suggestions = suggestions
        searchBar.setCustomSuggestionAdapter(searchSuggestionsAdapter)

        searchBar.addTextChangeListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.d("LOG_TAG", javaClass.simpleName + " text changed " + searchBar!!.text)
                // send the entered text to our filter and let it manage everything
                searchSuggestionsAdapter!!.filter.filter(searchBar!!.text)
            }

            override fun afterTextChanged(editable: Editable) {

            }

        })

        ticketsList.layoutManager = LinearLayoutManager(context)



    }

    override fun onClick(v: View?) {
        searchSuggestionsAdapter!!.addSuggestion(TicketType("Product", 100.0F))
    }

    override fun onDestroy() {
        super.onDestroy()
        suggestions.clear()
    }

    companion object {

        fun instance(): ShowFragment {
            return ShowFragment()
        }
    }

}
