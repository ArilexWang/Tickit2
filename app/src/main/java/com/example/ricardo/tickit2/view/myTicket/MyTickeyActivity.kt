package com.example.ricardo.tickit2.view.myTicket

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.data.network.repository.OrderRepository
import com.example.ricardo.tickit2.extensions.bindToSwipeRefresh
import com.example.ricardo.tickit2.extensions.getLocalUser
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.extensions.toast
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.detail.ShowDetailActivity
import com.example.ricardo.tickit2.view.admin.set.SetActivity
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

        ViewsActivity.start(this,3)
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

    override fun cancelOrderSuccess(message: String) {
        println(message)
    }

    fun createCategoryItemAdapter(ticket : Ticket) = TicketItemAdapter(ticket,{ticketDetail(ticket)})

    fun ticketDetail(ticket: Ticket){

        BottomSheet.Builder(this@MyTickeyActivity).sheet(R.menu.myorder_list).listener { dialog, which ->
            when (which) {
                R.id.detail_order -> {
                    AdvertisementActivity.start(this,ticket, ORDER_ARG)
                }
                R.id.cancel_order -> {
                    presenter.cancelOrder(ticket)
                }
            }
        }.build().show()



    }

    companion object {
        private const val ORDER_ARG = "ORDER_KEY"
    }

}