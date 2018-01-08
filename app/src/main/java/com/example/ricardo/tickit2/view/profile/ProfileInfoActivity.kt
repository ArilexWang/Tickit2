package com.example.ricardo.tickit2.view.profile

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.view.common.BaseActivity

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.Window
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.extensions.getIntent
import com.example.ricardo.tickit2.view.admin.main.AdminMainActivity
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.setting.SettingActivity
import com.example.ricardo.tickit2.view.signin.SignInActivity
import com.example.ricardo.tickit2.view.signin.SignInPresenter
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_profile_detail.*
import android.widget.SimpleAdapter
import android.widget.TextView
import com.orhanobut.dialogplus.*
import android.support.design.widget.Snackbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_setting.*
import com.orhanobut.dialogplus.DialogPlus






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

        usernameEdit.setOnClickListener { editUsername()}

        nicknameEdit.setOnClickListener { editNickname() }

        phoneEdit.setOnClickListener { editPhone() }



    }

    private fun editPhone() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

//        val isGrid: Boolean
//        val adapter = SimpleAdapter(this@ProfileInfoActivity, isGrid)
//        val dialog = DialogPlus.newDialog(this).setAdapter(adapter).setExpanded(true).create()
//        dialog.show()


    }

    private fun editNickname() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun editUsername() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
