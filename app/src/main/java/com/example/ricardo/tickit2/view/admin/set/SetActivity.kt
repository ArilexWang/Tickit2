package com.example.ricardo.tickit2.view.admin.set

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.view.Window
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.SHOW_ARG
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.model.Ticket
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.data.network.repository.OrderRepository
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.extensions.*
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.detail.SetDetailActivity
import com.example.ricardo.tickit2.view.admin.detail.ShowDetailActivity
import com.example.ricardo.tickit2.view.admin.main.AdminMainActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.myTicket.TicketItemAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_banner.*
import kotlinx.android.synthetic.main.item_ticket.*


class SetActivity :BaseActivity(), SetView {
    override val presenter by lazy { SetPresenter(this, BannerPicRepository.get(), ShowRepository.get(), OrderRepository.get()) }
    override var refresh by bindToSwipeRefresh(R.id.bannerSwipeRefreshView)

    val from by lazy { intent.getStringExtra(INTENT) }

    var userDao:GDUserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_banner)
        bannerRecyclerView.layoutManager = GridLayoutManager(this,1)

        userDao = loadDaoSession().gdUserDao
        presenter.mUserDao = userDao

        presenter.from = from

        if (from == SHOW_INTENT){
            toolbarTitle.setText("Show")
            bannerAdd.setOnClickListener{ addShowClick() }
        } else if(from == ORDER_INTENT){
            toolbarTitle.setText("Order")
            searchViewLayout.visibility = View.VISIBLE

            searchView.addOnTextChangedListener {
                onTextChanged{ text, start, before, Count ->
                    presenter.onSearchChanged(text)
                }
            }
        }else if(from == BANNER_INTENT){
            bannerAdd.setOnClickListener { addBtnClick() }
        }

        bannerSwipeRefreshView.setOnRefreshListener { presenter.onRefresh() }

        presenter.start()




        bannerBack.setOnClickListener{ backBtnClick() }
    }


    fun addBtnClick(){
        val banner = BannerPicture("","","",true)
        SetDetailActivity.startFromAdd(this,banner, ADD_INTEENT)
    }

    fun addShowClick(){
        val show = Show()
        ShowDetailActivity.start(this,show, SHOW_ARG)

    }

    fun createCategoryItemAdapter(banner : BannerPicture) = BannerItemAdapter(banner, {bannerPicClick(banner)})

    fun createCategoryItemAdapter(show: Show) = ShowItemAdapte(show,{showClick(show)})

    fun createCategoryItemAdapter(ticket: Ticket) = TicketItemAdapter(ticket,{orderClick(ticket)})

    fun bannerPicClick(banner: BannerPicture){

        BottomSheet.Builder(this@SetActivity).sheet(R.menu.set_banner_list).listener { dialog, which ->
            when (which) {
                R.id.set_banner -> {
                    SetDetailActivity.start(this,banner, BANNER_INTENT)
                }
                R.id.delete_banner -> {
                    presenter.deleteBanner(banner)
                }
            }
        }.build().show()

    }

    fun showClick(show: Show){
        BottomSheet.Builder(this@SetActivity).sheet(R.menu.set_banner_list).listener { dialog, which ->
            when (which) {
                R.id.set_banner -> {
                    ShowDetailActivity.start(this,show, SHOW_INTENT)
                }
                R.id.delete_banner -> {

                }
            }
        }.build().show()
    }

    fun orderClick(ticket: Ticket){
        BottomSheet.Builder(this@SetActivity).sheet(R.menu.set_order_list).listener { dialog, which ->
            when (which) {
                R.id.finish_order -> {
                    presenter.confirmOrder(ticket)
                }
                R.id.cancel_order -> {

                }
            }
        }.build().show()
    }

    fun backBtnClick(){
        AdminMainActivity.start(this)
    }


    override fun onSuccess(items: List<BannerPicture>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        bannerRecyclerView.adapter = SetListAdapter(categoryItemAdapters)
    }

    override fun onError(error: Throwable) {
        println(error)
    }

    override fun deleteSuccess(success: String) {
        presenter.onRefresh()
    }

    override fun deleteError(error: Throwable) {
        this.toast(error.toString())
    }


    override fun onShowSuccess(items: List<Show>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        bannerRecyclerView.adapter = SetListAdapter(categoryItemAdapters)
    }

    override fun onShowError(error: Throwable) {
    }

    override fun onOrderSuccess(items: List<Ticket>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        bannerRecyclerView.adapter = SetListAdapter(categoryItemAdapters)
    }

    override fun onOrderError(error: Throwable) {

        println(error)

    }


    companion object {

        private const val BANNER_ARG = "Banner_Key"
        private const val INTENT = "Intent_From"


        private const val BANNER_INTENT = "MAIN_BANNER"
        private const val SHOW_INTENT = "MAIN_SHOW"
        private const val ORDER_INTENT = "MAIN_ORDER"
        private const val ADD_INTEENT = "MAIN_ADD"

        fun getIntent(context: Context,from: String) = context.getIntent<SetActivity>()
                .apply {
                    putExtra(SetActivity.INTENT,from)
                }

        fun start(context: Context,from: String){
            val intent = getIntent(context,from)
            context.startActivity(intent)
        }


    }


}