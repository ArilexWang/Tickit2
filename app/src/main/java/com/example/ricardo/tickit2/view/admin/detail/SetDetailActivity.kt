package com.example.ricardo.tickit2.view.admin.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.repository.BannerPicRepository
import com.example.ricardo.tickit2.extensions.extra
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.set.SetActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.camera.CameraActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_banner_setting.*
import android.widget.AdapterView.OnItemSelectedListener
import com.example.ricardo.tickit2.data.*
import com.example.ricardo.tickit2.data.network.repository.ShowRepository


class SetDetailActivity :BaseActivity(), SetDetailView {
    override val presenter by lazy{ SetDetailPresenter(this, BannerPicRepository.get(), ShowRepository.get()) }

    val banner: BannerPicture by extra(BANNER_ARG)

    val show: Show by extra(SHOW_ARG)

    val from: String by lazy { intent.getStringExtra(INTENT) }

    var userDao: GDUserDao? = null

    var newBanner: BannerPicture? = null

    var newShow: Show? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_setting)

        Fresco.initialize(this)

        userDao = loadDaoSession().gdUserDao

        if (from == BANNER_INTENT || from == ADD_INTEENT){
            set_banner_pic.setImageURI(banner.picPath)
            set_descriptionURL.setText(banner.targetPath)

            set_banner_pic.setOnClickListener { picClick() }

            if(from != ADD_INTEENT){
                set_banner_save.setOnClickListener{ savaBannerBtnClick() }
            } else {
                set_banner_save.setOnClickListener { addBannerBtnClick() }
            }

            newBanner = BannerPicture(banner)

            switch_view.isChecked = newBanner!!.isOnDisplay

            switch_view.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {  buttonView, isChecked ->
                if (isChecked) {
                    newBanner!!.isOnDisplay = isChecked
                } else {
                    newBanner!!.isOnDisplay = isChecked
                }
            })

            set_bannerBack.setOnClickListener { backClick() }
        }

        presenter.mUserDao = userDao


    }


    override fun onSuccess(items: List<BannerPicture>) {
        SetActivity.start(this, BANNER_INTENT)
    }

    override fun onError(error: Throwable) {
        println(error)
    }


    override fun createSuccess(items: List<BannerPicture>) {
        SetActivity.start(this, BANNER_INTENT)
    }

    override fun createFaile(error: Throwable) {
        println(error)
    }

    override fun setShowSuccess(items: List<Show>) {
        SetActivity.start(this, SHOW_INTENT)
    }

    override fun setShowError(error: Throwable) {
        println(error)
    }

    fun backClick(){
        SetActivity.start(this, BANNER_INTENT)
    }

    fun backToShowClick(){
        SetActivity.start(this, SHOW_INTENT)
    }

    fun savaBannerBtnClick(){
        newBanner!!.targetPath = set_descriptionURL.text.toString()
        presenter.setBanner(newBanner!!)
    }

    fun addBannerBtnClick(){
        println("bbbbbbbbb")
        newBanner!!.targetPath = set_descriptionURL.text.toString()
        presenter.createBanner(newBanner!!)
    }

    fun saveShowBtnClick(){
        newShow!!.name = set_name.text.toString()
        newShow!!.descriptionPath = set_descriptionURL.text.toString()

        if (newShow!!.isRestricted){
            newShow!!.restrictionNum = set_restrictionNum.text!!.toString().toInt()
        }


        presenter.setShow(newShow!!)

    }




    fun picClick(){
        BottomSheet.Builder(this@SetDetailActivity).sheet(R.menu.list).listener { dialog, which ->
            when (which) {
                R.id.takePhoto -> {
                    val intent = Intent()
                    intent.setClass(this@SetDetailActivity, CameraActivity::class.java)
                    startActivity(intent)
                }
                R.id.choosePhoto -> {
                    newBanner!!.targetPath = set_descriptionURL.text.toString()
                    PhotoChoseActivity.startFromBanner(this,"banner",newBanner!!)

                }
            }
        }.build().show()
    }

    companion object {
        private const val BANNER_ARG = "Banner_Key"
        private const val SHOW_ARG = "SHOW_KET"
        private const val INTENT = "INTENT_FROM"

        fun getIntent(context: Context, banner: BannerPicture) = context
                .getIntent<SetDetailActivity>()
                .apply { putExtra(BANNER_ARG, banner) }

        fun getIntent(context: Context, banner: BannerPicture, from: String) = context
                .getIntent<SetDetailActivity>()
                .apply {
                    putExtra(BANNER_ARG,banner)
                    putExtra(INTENT,from)
                }


        fun getIntent(context: Context,show: Show,from: String) = context
                .getIntent<SetDetailActivity>()
                .apply {
                    putExtra(SHOW_ARG,show)
                    putExtra(INTENT,from)
                }

        fun start(context: Context, banner: BannerPicture,from: String ){
            val intent = getIntent(context, banner,from)
            context.startActivity(intent)
        }

        fun startFromAdd(context: Context,banner: BannerPicture,from: String){
            val intent = getIntent(context,banner,from)
            context.startActivity(intent)
        }

        fun startFromShow(context: Context,show: Show,from: String){
            val intent = getIntent(context,show,from)
            context.startActivity(intent)
        }

    }

}