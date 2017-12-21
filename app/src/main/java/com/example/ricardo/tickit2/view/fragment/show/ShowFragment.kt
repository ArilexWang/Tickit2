package com.example.ricardo.tickit2.view.fragment.show

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.R.id.mListView
import kotlinx.android.synthetic.main.fragment_show.*
import java.util.ArrayList

/**
 * Created by yuhanyin on 2017/12/8.
 */
class ShowFragment :android.support.v4.app.Fragment(){


    internal var mSearchBar: SearchBar? = null
    internal var mList = ArrayList<String>()
    internal var mAdappter: MyAdapter? = null
    internal var layout: LinearLayout? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_show, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mListView.setEnableRefresh(true)
        //关闭刷新功能，在 addHeaderView 之前调用

        mSearchBar = SearchBar(context)

        layout = LayoutInflater.from(context).inflate(R.layout.header_test, null) as LinearLayout
        layout?.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mSearchBar?.setTextEditable(true)
        layout?.setOnClickListener(View.OnClickListener {
            Log.e("HomeFragment", "SearchBar click")
        })
//        layout.addView(mSearchBar);
        mListView?.addHeaderView(mSearchBar)


    }

    fun testForPullToRefresh() {
        mListView?.pullRefreshEnable(true)//下拉刷新
        mListView?.setAutoFetchMore(true)//自动加载更多

        mListView?.postDelayed(Runnable { mListView?.onRefreshComplete() }, 3000)

        mListView?.setOnRefreshListener(object : SearchListView.OnRefreshListener {
            override fun onRefresh() {

                mListView?.postDelayed(Runnable {
                    mList.clear()
                    for (i in 0..9) {
                        val str = "item" + (i + 1)
                        mList.add(str)
                    }
                    mAdappter?.notifyDataSetChanged()
                    mListView?.onRefreshComplete()
                }, 3000)
            }
        })

        mListView?.setOnLastItemVisibleListener(object : SearchListView.OnLastItemVisibleListener {
            override fun onLastItemVisible() {
                mListView?.postDelayed(Runnable {
                    val start = mList.size
                    for (i in start until start + 10) {
                        val str = "item" + (i + 1)
                        mList.add(str)
                    }
                    mAdappter?.notifyDataSetChanged()
                    mListView?.onRefreshComplete()
                    if (mList.size > 30) {
                        mListView?.setLoadAll()
                    }
                }, 3000)

            }
        })
    }


    companion object {

        fun instance(): ShowFragment {
            return ShowFragment()
        }
    }


    internal inner class MyAdapter : BaseAdapter() {

        override fun getCount(): Int {
            return mList.size
        }

        override fun getItem(position: Int): String {
            return mList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
            val view = LayoutInflater.from(context).inflate(R.layout.activity_views, null)
            val textView = view.findViewById<View>(R.id.textView) as TextView
            textView.text = getItem(position)

            return view
        }
    }
}