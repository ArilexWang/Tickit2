package com.example.ricardo.tickit2.view.fragment.show

import android.content.Intent
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
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.extensions.toast
import com.example.ricardo.tickit2.view.advertisement.AdvertisementActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_myticket.*


@Suppress("UNREACHABLE_CODE")
/**
 * Created by yuhanyin on 2017/12/8.
 */
class ShowFragment :android.support.v4.app.Fragment(), View.OnClickListener, ShowFragmentView {
    override var refresh: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}


    private var suggestions = ArrayList<TicketType>()
    private var searchSuggestionsAdapter: SearchSuggestionsAdapter? = null
    private var showList: RecyclerView? = null

    // Sample data
    private val tickets = arrayOf("天鹅湖",
            "Vincent",
            "猫Cats",
            "奇迹展",
            "芳华——舞团",
            "C4专场")


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Fresco.initialize(context)
        val view = inflater!!.inflate(R.layout.fragment_show, null)
        showList = view.findViewById(R.id.ticketList)
        return view

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



//        ticketList.layoutManager = LinearLayoutManager(context)





    }

    override fun onClick(v: View?) {
        searchSuggestionsAdapter!!.addSuggestion(TicketType("Product", 100.0F))
    }

    override fun onDestroy() {
        super.onDestroy()
        suggestions.clear()
    }

    override fun show(items: List<Show>) {

        showList!!.layoutManager = GridLayoutManager(context, 1)

        val showItemAdapters = items.map(this::createShowItemAdapter)
        showList!!.adapter = ShowListAdapter(showItemAdapters)
    }
    fun createShowItemAdapter(show: Show) = ShowItemAdapter(show,{viewClick(show)})



    //票务新奇日点击事件
    fun viewClick(show: Show){
        val intent = Intent(context, AdvertisementActivity::class.java)
        intent.putExtra("url", show.descriptionPath)
        intent.putExtra("category",show.category)
        intent.putExtra("id",show.id)
        startActivityForResult(intent, 0)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(context,"Error: ${error.message}", Toast.LENGTH_LONG).show()
//        toast("Error: ${error.message}")
        println(error)
    }

    companion object {

        fun instance(): ShowFragment {
            return ShowFragment()
        }
    }

}
