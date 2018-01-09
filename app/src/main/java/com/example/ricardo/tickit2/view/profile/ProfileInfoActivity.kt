package com.example.ricardo.tickit2.view.profile

import android.content.Context
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.view.common.BaseActivity

import android.content.Intent
import android.view.Window
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.extensions.extra
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.camera.CameraActivity
import com.example.ricardo.tickit2.view.signin.SignInActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_profile_detail.*

class ProfileInfoActivity: BaseActivity(),ProfileInfoView{
    override val presenter by lazy { ProfileInfoPresenter() }

    val user: User by extra(USER_ARG)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_profile_detail)
        val userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = userDao

        val avatarPath = presenter.getLocalUser()!!.avatar
        val userName = presenter.getLocalUser()!!.nickName

        user_item_username_tv_username.setText(userName)

        userItemAvatar.setImageURI(avatarPath)

        userItemAvatar.setOnClickListener{ avatarClick()  }

        user_btn_exit_login.setOnClickListener{ exitLogin() }

        profileDetialBack.setOnClickListener { backClick() }

    }

    fun backClick(){
        ViewsActivity.start(this,3)
    }


    fun exitLogin(){
        val intent = Intent()
        intent.setClass(this@ProfileInfoActivity, SignInActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val userDao = loadDaoSession().gdUserDao
        presenter.mUserDao = userDao
        var avatarPath =  presenter.getLocalUser()!!.avatar
        userItemAvatar.setImageURI(avatarPath)

    }

    fun avatarClick(){
        //底部导航栏
        BottomSheet.Builder(this@ProfileInfoActivity).sheet(R.menu.list).listener { dialog, which ->
            when (which) {
                R.id.takePhoto -> {

                    val intent = Intent()
                    intent.setClass(this@ProfileInfoActivity, CameraActivity::class.java)
                    startActivity(intent)
                }

                R.id.choosePhoto -> {
                    PhotoChoseActivity.start(this,"avatar")
                }

            }
        }.build().show()
    }

    companion object {
        val USER_ARG = "USER_KEY"
        fun getIntent(context: Context,user: User) = context
                .getIntent<ProfileInfoActivity>()
                .apply { putExtra(USER_ARG,user) }

        fun start(context: Context,user: User){
            val intent = ProfileInfoActivity.getIntent(context,user)
            context.startActivity(intent)
        }




    }
}
