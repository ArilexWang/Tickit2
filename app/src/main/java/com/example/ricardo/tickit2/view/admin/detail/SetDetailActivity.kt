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
import com.example.ricardo.tickit2.view.setting.SettingActivity
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

        if (from == BANNER_INTENT){
            set_banner_pic.setImageURI(banner.picPath)
            set_descriptionURL.setText(banner.targetPath)

            set_banner_pic.setOnClickListener { picClick() }

            if(from != ADD_INTEENT){
                set_banner_save.setOnClickListener{ savaBannerBtnClick() }
            } else{
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

        else if (from == SHOW_INTENT){
            set_title.setText("Show")

            set_banner_pic.setImageURI(show.avatarPath)
            var para = set_banner_pic.layoutParams
            para.width = 300
            para.height = 400
            set_banner_pic.layoutParams = para

            cate_spinner.visibility = View.VISIBLE
            switch_limit.visibility = View.VISIBLE

            cate_spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val data = cate_spinner.getItemAtPosition(position) as String//从spinner中获取被选择的数据
                    if (data == PWXQR){
                        newShow!!.category = PWXQR_NUMBER
                    } else if(data == ODNR){
                        newShow!!.category = ODNR_NUMBER
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            })
            if(from != ADD_INTEENT){
                set_banner_save.setOnClickListener{ saveShowBtnClick() }
            } else{

            }

            set_descriptionURL.setText(show.descriptionPath)
            set_input2.visibility = View.VISIBLE
            set_name.setText(show.name)

            switch_view.textOn = "在售"
            switch_view.textOff = "停售"

            newShow = show
            switch_view.isChecked = newShow!!.is_OnSale

            switch_view.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {  buttonView, isChecked ->
                if (isChecked) {
                    newShow!!.is_OnSale = isChecked

                } else {
                    newShow!!.is_OnSale = isChecked

                }
            })

            set_restrictionNum.setText(newShow!!.restrictionNum.toString())

            switch_limit.isChecked = newShow!!.isRestricted
            switch_limit.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {  buttonView, isChecked ->
                if (isChecked) {
                    newShow!!.isRestricted = isChecked
                    set_input3.visibility = View.VISIBLE
                } else {
                    newShow!!.isRestricted = isChecked
                    set_input3.visibility = View.GONE
                }
            })


            set_bannerBack.setOnClickListener { backToShowClick() }
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
                    intent.setClass(this@SetDetailActivity, SettingActivity::class.java)
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