package com.example.ricardo.tickit2.view.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.entity.GDUser
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.setting.SettingPresenter
import kotlinx.android.synthetic.main.activity_profile_detail.*
import android.content.DialogInterface
import android.R.menu
import android.content.Intent
import com.example.ricardo.tickit2.MainActivity
import com.cocosw.bottomsheet.BottomSheet
import com.example.ricardo.tickit2.view.photo.PhotoChoseActivity
import com.example.ricardo.tickit2.view.setting.SettingActivity
import com.example.ricardo.tickit2.view.signup.SignUpActivity


/**
 * Created by yuhanyin on 2017/12/13.
 */
class ProfileInfoActivity: BaseActivity(),ProfileInfoView{
   override val presenter by lazy { ProfileInfoPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState != null){
            print("not the first time")
        }else{
            setContentView(R.layout.activity_profile_detail)

            val userDao = loadDaoSession().gdUserDao

            presenter.mUserDao = userDao


            val avatarPath = presenter.getLocalAvatar()

            user_item_avatar.setImageURI(avatarPath)

            user_item_avatar.setOnClickListener{ avatarClick()  }
        }



    }


    override fun onResume() {
        super.onResume()

        println("resume")


        val userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = userDao


        val avatarPath =  "http://" + presenter.getLocalAvatar()

        println(avatarPath)

        user_item_avatar.setImageURI(avatarPath)

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
                    val intent = Intent()
                    intent.setClass(this@ProfileInfoActivity, PhotoChoseActivity::class.java)
                    startActivity(intent)
                    this.finish()

                }
            }
        }.build().show()
    }

    private fun initView(){


    }
    companion object {

        val PROFILE_RESULT_CODE = 30001
    }
}
