package com.example.ricardo.tickit2.view.profile

import android.app.Activity
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.view.common.BaseActivity

import android.content.Intent
import android.view.Window
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.setting.SettingActivity
import com.example.ricardo.tickit2.view.signin.SignInActivity
import com.example.ricardo.tickit2.view.signin.SignInPresenter
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_profile_detail.*

class ProfileInfoActivity: BaseActivity(),ProfileInfoView{
   override val presenter by lazy { ProfileInfoPresenter() }
   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_profile_detail)
        initView()

        val userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = userDao


        val avatarPath = presenter.getLocalAvatar()

        userItemAvatar.setImageURI(avatarPath)

        userItemAvatar.setOnClickListener{ avatarClick()  }

        user_btn_exit_login.setOnClickListener{ exitLogin() }

        profileDetialBack.setOnClickListener { backClick() }

    }

    fun backClick(){
        val intent = Intent()

        val bundle = Bundle()
        bundle.putString("view","4")

        intent.setClass(this@ProfileInfoActivity,ViewsActivity::class.java)
        intent.putExtra("profiledetialflag", 1)
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }



    fun exitLogin(){
        val intent = Intent()
        intent.setClass(this@ProfileInfoActivity, SignInActivity::class.java)
        startActivity(intent)
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
        //底部导航栏
        BottomSheet.Builder(this@ProfileInfoActivity).sheet(R.menu.list).listener { dialog, which ->
            when (which) {
                R.id.takePhoto -> {

                    val intent = Intent()
                    intent.setClass(this@ProfileInfoActivity, SettingActivity::class.java)
                    startActivity(intent)
                }

                R.id.choosePhoto -> {
                    PhotoChoseActivity.start(this,"avatar")
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
