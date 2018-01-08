package com.example.ricardo.tickit2.view.myTicket

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.data.network.repository.OrderRepository
import com.example.ricardo.tickit2.extensions.bindToSwipeRefresh
import com.example.ricardo.tickit2.extensions.getLocalUser
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.extensions.toast
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.advertisement.AdvertisementActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_myticket.*

/**
 * Created by Ricardo on 2017/12/29.
 */

class MyTickeyActivity:BaseActivity(),MyTicketView{
    override val presenter by lazy { MyTicketPresenter(this, OrderRepository.get()) }

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    var _userDao: GDUserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_myticket)
        Fresco.initialize(this)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        _userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = _userDao

        val user = presenter.getLocalUser(_userDao!!)

        presenter.getOrder(user!!)

        ticketDetialBack.setOnClickListener { ticketBackBtnClick() }

        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }

    }

    fun ticketBackBtnClick(){
        val intent = Intent()

        val bundle = Bundle()
        bundle.putString("view","4")

        intent.setClass(this@MyTickeyActivity, ViewsActivity::class.java)
        intent.putExtra("ticketdetialflag", 1)
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    //加载我的票务成功
    override fun show(items: List<Ticket>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        recyclerView.adapter = MyTicketListAdapter(categoryItemAdapters)
    }

    //加载我的票务失败
    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        println(error)
    }

    fun createCategoryItemAdapter(ticket : Ticket) = TicketItemAdapter(ticket,{ticketDetail(ticket)})

    fun ticketDetail(ticket: Ticket){
        val url = ticket.showDescription
        val intent = Intent()
        intent.putExtra("url", url)
        intent.setClass(this@MyTickeyActivity,AdvertisementActivity::class.java)
        startActivity(intent)
    }


}