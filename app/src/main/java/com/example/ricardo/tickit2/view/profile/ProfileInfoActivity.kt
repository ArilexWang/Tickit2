package com.example.ricardo.tickit2.view.profile

import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.view.common.BaseActivity

import android.content.Intent
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_profile_detail.*

/**
 * Created by yuhanyin on 2017/12/13.
 */
class ProfileInfoActivity: BaseActivity(),ProfileInfoView{
   override val presenter by lazy { ProfileInfoPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)
        initView()

        val userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = userDao


        val avatarPath = presenter.getLocalAvatar()

        userItemAvatar.setImageURI(avatarPath)

        userItemAvatar.setOnClickListener{ avatarClick()  }

    }


    override fun onResume() {
        super.onResume()

        println("resume")

        val userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = userDao


        var avatarPath =  presenter.getLocalAvatar()

        println(avatarPath)

        userItemAvatar.setImageURI(avatarPath)

    }


    fun avatarClick(){

        println("click")

        //底部导航栏
        BottomSheet.Builder(this@ProfileInfoActivity).sheet(R.menu.list).listener { dialog, which ->
            when (which) {
                R.id.takePhoto -> {
                    val intent = Intent()
                    intent.setClass(this@ProfileInfoActivity, SettingActivity::class.java)
                    startActivity(intent)
                }
                R.id.choosePhoto -> {
                    val intent = Intent()
                    intent.setClass(this@ProfileInfoActivity, PhotoChoseActivity::class.java)
                    startActivity(intent)
                    this.finish()

                }
            }
        }.build().show()
    }

    private fun initView(){
        userItemAvatar.setOnClickListener {
            val intent = Intent()
            intent.setClass(this.applicationContext, SettingActivity::class.java)
            startActivity(intent)
        }


    }
    companion object {

        val PROFILE_RESULT_CODE = 30001
    }
}
