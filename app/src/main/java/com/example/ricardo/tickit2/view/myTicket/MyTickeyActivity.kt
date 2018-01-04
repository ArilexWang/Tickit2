package com.example.ricardo.tickit2.view.myTicket

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Window
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.entity.Ticket
import com.example.ricardo.tickit2.data.model.Order
import com.example.ricardo.tickit2.data.network.repository.OrderRepository
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_myticket.*
import kotlinx.android.synthetic.main.activity_profile_detail.*

/**
 * Created by Ricardo on 2017/12/29.
 */

class MyTickeyActivity:BaseActivity(),MyTicketView{
    override val presenter by lazy { MyTicketPresenter(this, OrderRepository.get()) }

    var _userDao: GDUserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_myticket)

        recyclerView.layoutManager = GridLayoutManager(this, 1)

        _userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = _userDao

        val user = presenter.getLocalUser()

        presenter.getOrder(user!!)

        ticketDetialBack.setOnClickListener { ticketBackBtnClick() }

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

    override fun show(items: List<Order>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        recyclerView.adapter = MyTicketListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {

    }

    fun createCategoryItemAdapter(order : Order) = TicketItemAdapter(order)

}