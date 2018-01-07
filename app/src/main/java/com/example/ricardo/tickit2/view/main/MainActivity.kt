package com.example.ricardo.tickit2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.repository.UserRepository
import com.example.ricardo.tickit2.extensions.isAdmin
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.extensions.saveUserToLocal
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.main.AdminMainActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.main.MainActivityPresenter
import com.example.ricardo.tickit2.view.main.MainView
import com.example.ricardo.tickit2.view.signin.SignInActivity
import com.example.ricardo.tickit2.view.signin.SignInPresenter
import com.example.ricardo.tickit2.view.signin.SignInView
import com.example.ricardo.tickit2.view.signup.SignUpActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signup.*

class MainActivity : BaseActivity(),MainView {

    override val presenter by lazy { MainActivityPresenter(this, UserRepository.get()) }

    var _userDao: GDUserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _userDao = loadDaoSession().gdUserDao


        presenter.mUserDao = _userDao


        presenter.start()

        signin_entry.setOnClickListener {
            // Finish the registration screen and return to the Login activity
            val intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_in_top )
            //第一个参数为第一个Activity离开时的动画，第二参数为所进入的Activity的动画效果

        }

        signup_entry.setOnClickListener{
            // Finish the registration screen and return to the Login activity
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
            finish()
            overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_in_top )
            //第一个参数为第一个Activity离开时的动画，第二参数为所进入的Activity的动画效果
        }

    }

    override fun onSuccess(items: List<User>) {
        val user = items[0]
        saveUserToLocal(user, presenter.mUserDao!!)

        println(user.id)


        if (isAdmin(_userDao!!)){
            val intent = Intent()
            intent.setClass(this@MainActivity, AdminMainActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent()
            intent.setClass(this@MainActivity, ViewsActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onError(error: Throwable) {

    }
}
