package com.example.ricardo.tickit2.view.signin

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.*
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.repository.UserRepository
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.extensions.saveUserToLocal
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.admin.main.AdminMainActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.signup.SignUpActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_signin.*

/**
 * Created by Ricardo on 2017/11/13.
 */

class SignInActivity:BaseActivity(),SignInView{
    override val presenter by lazy { SignInPresenter(this, UserRepository.get()) }

    var _userDao: GDUserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        _userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = _userDao

        btn_signin.setOnClickListener{ signinBtnClick(presenter) }

        link_signup.setOnClickListener{ linkSignupClick() }

        getLocalUser(_userDao!!)

    }

    fun getLocalUser(userDao: GDUserDao){
        val db = userDao!!.queryBuilder()

        val list = db.list()

        if (!list.isEmpty()){
            val id = list[0].id
            val password = list[0].password

            input_studentID.setText(id)
            input_password.setText(password)
        }


    }

    fun linkSignupClick(){
        val intent = Intent()
        intent.setClass(this@SignInActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun signinBtnClick(presenter: SignInPresenter){
        if (validate()){
            val id = input_studentID.getText().toString()
            val password = input_password.getText().toString()
            println(password)
            presenter.postAccount(id,password)
        }
    }


    //登录成功后调用函数
    override fun onSuccess(items: List<User>) {
        val user = items[0]
        saveUserToLocal(user, presenter.mUserDao!!)


        if (user.isAdmin == false){
            //跳转界面
            val intent = Intent()
            intent.setClass(this@SignInActivity, ViewsActivity::class.java)
            startActivity(intent)
        } else{
            val intent = Intent()
            intent.setClass(this@SignInActivity, AdminMainActivity::class.java)
            startActivity(intent)
        }


    }

    //登录失败后调用函数
    override fun onError(error: Throwable) {
        println(error.message)
        if (error.message == USER_NOT_EXIT_CODE){
            Toast.makeText(baseContext, USER_NOT_EXIT , Toast.LENGTH_SHORT).show()
        }else if(error.message == PASSWORK_ERROR_CODE){
            Toast.makeText(baseContext, PASSWORK_ERROR , Toast.LENGTH_SHORT).show()
        }

    }

    fun validate(): Boolean {
        var valid = true

        val inputID = input_studentID.getText().toString()
        val password = input_password.getText().toString()

        if (inputID.isEmpty()|| ! Patterns.PHONE.matcher(inputID).matches()) {
            //|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            input_studentID.setError("enter a valid email address !")
            valid = false
        } else {
            input_studentID.setError(null)
        }

        //password should be 4-10
        if (password.isEmpty() || password.length < 0 || password.length > 10) {
            input_password.setError("between 4 and 10 alphanumeric characters")
            valid = false
        } else {
            input_password.setError(null)
        }

        return valid
    }
}