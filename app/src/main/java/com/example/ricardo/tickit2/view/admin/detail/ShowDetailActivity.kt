
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
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.set.SetActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.camera.CameraActivity
import com.facebook.drawee.backends.pipeline.Fresco
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ImageView
import com.example.ricardo.tickit2.data.*
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import android.widget.TextView
import com.bigkoo.pickerview.listener.CustomListener
import com.bigkoo.pickerview.TimePickerView
import com.example.ricardo.tickit2.extensions.*
import kotlinx.android.synthetic.main.activity_show_setting.*
import java.text.SimpleDateFormat
import java.util.*


class ShowDetailActivity : BaseActivity(), ShowDetailView{
    override val presenter by lazy{ ShowDetailPresenter(this, ShowRepository.get()) }

    val banner: BannerPicture by extra(BANNER_ARG)

    val show: Show by extra(SHOW_ARG)

    val from: String by lazy { intent.getStringExtra(INTENT) }

    var userDao: GDUserDao? = null

    var newBanner: BannerPicture? = null

    var newShow: Show? = null

    var expiredTimePicker:TimePickerView? = null
    var expiredFetchTimePicker: TimePickerView? = null

    var expiredTime: String? = DEFAULT_TIME
    var expiredFetchTime: String? = DEFAULT_TIME


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_setting)

        Fresco.initialize(this)

        userDao = loadDaoSession().gdUserDao

        set_title.setText("Show")

        set_banner_pic.setImageURI(show.avatarPath)

        cate_spinner.visibility = View.VISIBLE
        switch_limit.visibility = View.VISIBLE


        cate_spinner.setSelection(show.category)

        cate_spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val data = cate_spinner.getItemAtPosition(position) as String//从spinner中获取被选择的数据
                if (data == PWXQR){
                    newShow!!.category = PWXQR_NUMBER
                } else if(data == ODNR){
                    newShow!!.category = ODNR_NUMBER
                } else if (data == ACTIVITY){
                    newShow!!.category = ACTI_NUMBER
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

        switch_view.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                newShow!!.is_OnSale = isChecked

            } else {
                newShow!!.is_OnSale = isChecked

            }
        })

        set_restrictionNum.setText(newShow!!.restrictionNum.toString())

        switch_limit.isChecked = newShow!!.isRestricted

        if (newShow!!.isRestricted){
            set_input3.visibility = View.VISIBLE
        }

        switch_limit.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                newShow!!.isRestricted = isChecked
                set_input3.visibility = View.VISIBLE
            } else {
                newShow!!.isRestricted = isChecked
                set_input3.visibility = View.GONE
            }
        })


        show_expiredTime.setText(setTime(show.expiredTime))
        show_expiredFetchTime.setText(setTime(show.expiredFetchTime))
        set_remain.setText(show.remainAmout.toString())

        initExpiredTimePicker()
        initExpiredFetchTimePicker()


        set_bannerBack.setOnClickListener { backToShowClick() }

        btn_CustomTime.setOnClickListener{ expiredTimePicker!!.show(View(this)) }

        btn_saveFetchTime.setOnClickListener{ expiredFetchTimePicker!!.show(View(this)) }

        presenter.mUserDao = userDao


    }



    fun expiredTimeChosen(datastr: String?){
        val strData = getTimeString(datastr!!)
        expiredTime = strData
        btn_CustomTime.setText("修改时间")
        show_expiredTime.setText(setTime(strData))
    }

    fun expiredFetchTimeChosen(datastr: String){
        val strData = getTimeString(datastr!!)
        expiredFetchTime = strData
        btn_CustomTime.setText("修改时间")
        show_expiredFetchTime.setText(setTime(strData))
    }

    private fun initExpiredTimePicker() {

        val selectedDate = Calendar.getInstance()//系统当前时间
        val startDate = Calendar.getInstance()
        startDate.set(2014, 1, 23)
        val endDate = Calendar.getInstance()
        endDate.set(2027, 2, 28)
        //时间选择器 ，自定义布局
        expiredTimePicker = TimePickerView.Builder(this, object : TimePickerView.OnTimeSelectListener {
            override fun onTimeSelect(date: Date, v: View) {//选中事件回调
                btn_CustomTime.setText(getTime(date))
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, object:CustomListener {
                    public override fun customLayout(v:View) {
                        val tvSubmit = v.findViewById<TextView>(R.id.tv_finish) as TextView
                        val ivCancel = v.findViewById<ImageView>(R.id.iv_cancel) as ImageView
                        tvSubmit.setOnClickListener(object:View.OnClickListener {
                            public override fun onClick(v:View) {

                                expiredTimePicker!!.returnData()

                                expiredTimeChosen(btn_CustomTime.text.toString())

                                expiredTimePicker!!.dismiss()


                            }
                        })
                        ivCancel.setOnClickListener(object:View.OnClickListener {
                            public override fun onClick(v:View) {
                                expiredTimePicker!!.dismiss()
                            }
                        })
                    }
                })
                .setContentSize(18)
                .setType(booleanArrayOf(true, true, true, true, true, false))
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(-0xdb5263)
                .build()

    }

    private fun initExpiredFetchTimePicker(){
        val selectedDate = Calendar.getInstance()//系统当前时间
        val startDate = Calendar.getInstance()
        startDate.set(2014, 1, 23)
        val endDate = Calendar.getInstance()
        endDate.set(2027, 2, 28)
        //时间选择器 ，自定义布局
        expiredFetchTimePicker = TimePickerView.Builder(this, object : TimePickerView.OnTimeSelectListener {
            override fun onTimeSelect(date: Date, v: View) {//选中事件回调
                btn_CustomTime.setText(getTime(date))
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, object:CustomListener {
                    public override fun customLayout(v:View) {
                        val tvSubmit = v.findViewById<TextView>(R.id.tv_finish) as TextView
                        val ivCancel = v.findViewById<ImageView>(R.id.iv_cancel) as ImageView
                        tvSubmit.setOnClickListener(object:View.OnClickListener {
                            public override fun onClick(v:View) {

                                expiredFetchTimePicker!!.returnData()

                                expiredFetchTimeChosen(btn_CustomTime.text.toString())

                                expiredFetchTimePicker!!.dismiss()


                            }
                        })
                        ivCancel.setOnClickListener(object:View.OnClickListener {
                            public override fun onClick(v:View) {
                                expiredFetchTimePicker!!.dismiss()
                            }
                        })
                    }
                })
                .setContentSize(18)
                .setType(booleanArrayOf(true, true, true, true, true, false))
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(-0xdb5263)
                .build()

    }



    private fun getTime(date: Date): String {//可根据需要自行截取数据显示
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(date)
    }

    override fun setShowSuccess(items: List<Show>) {
        SetActivity.start(this, SHOW_INTENT)
    }

    override fun setShowError(error: Throwable) {
        println(error)
    }

    fun backToShowClick(){
        SetActivity.start(this, SHOW_INTENT)
    }

    fun saveShowBtnClick(){
        newShow!!.name = set_name.text.toString()
        newShow!!.descriptionPath = set_descriptionURL.text.toString()
        newShow!!.expiredTime = expiredTime!!
        newShow!!.expiredFetchTime = expiredFetchTime!!
        newShow!!.remainAmout = set_remain.text.toString().toInt()

        if (newShow!!.isRestricted){
            newShow!!.restrictionNum = set_restrictionNum.text!!.toString().toInt()
        }
        presenter.setShow(newShow!!)

    }

    fun picClick(){
        BottomSheet.Builder(this@ShowDetailActivity).sheet(R.menu.list).listener { dialog, which ->
            when (which) {
                R.id.takePhoto -> {
                    val intent = Intent()
                    intent.setClass(this@ShowDetailActivity, CameraActivity::class.java)
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


        fun getIntent(context: Context, show: Show, from: String) = context
                .getIntent<ShowDetailActivity>()
                .apply {
                    putExtra(SHOW_ARG,show)
                    putExtra(INTENT,from)
                }


        fun startFromAdd(context: Context, show: Show, from: String){
            val intent = getIntent(context,show,from)
            context.startActivity(intent)
        }

        fun start(context: Context, show: Show, from: String){
            val intent = getIntent(context,show,from)
            context.startActivity(intent)
        }

    }

}