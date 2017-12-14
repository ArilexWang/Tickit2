package com.example.ricardo.tickit2.view.signup

import android.content.Intent
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.entity.GDUser
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.repository.UserRepository
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.signin.SignInActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : BaseActivity(),SignUpView{

    override val presenter by lazy { SignUpPresenter(this, UserRepository.get()) }

    var _userDao: GDUserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        _userDao = loadDaoSession().gdUserDao

        presenter.mUserDao = _userDao

        btn_signup.setOnClickListener{ signupClick(presenter) }

        link_signin.setOnClickListener{ signinLinkBtnClick() }



    }

    //按钮点击处理事件
    fun signupClick(presenter: SignUpPresenter?){
        var tUser = User()

        //如果输入合法
        if (validate()){

            tUser.nickName = input_nickname.getText().toString()
            tUser.id = input_studentID.getText().toString()
            tUser.realName = input_realName.getText().toString()
            tUser.mobileNumber = input_mobile.getText().toString()
            tUser.password = input_password.getText().toString()

            presenter!!.postAccount(tUser)
        }


    }

    fun signinLinkBtnClick(){
        val intent = Intent()
        intent.setClass(this@SignUpActivity, ViewsActivity::class.java)
        startActivity(intent)
    }

    //检查输入是否合法
    fun validate(): Boolean {
        var valid = true
        val nickname = input_nickname.getText().toString()
        val studentID = input_studentID.getText().toString()
        val realName = input_realName.getText().toString()
        val mobile = input_mobile.getText().toString()
        val password = input_password.getText().toString()
        val reEnterPassword = input_reEnterPassword.getText().toString()

        if (nickname.isEmpty() || nickname.length < 3) {
            input_nickname.setError("at least 3 characters")
            valid = false
        } else {
            input_nickname.setError(null)
        }

        // todo: check if student ID in list
        if (studentID.isEmpty() || studentID.length != 7) {
            input_studentID.setError("Enter Valid Address")
            valid = false
        } else {
            input_studentID.setError(null)
        }

        // todo: check if name matches the student ID
        if (realName.isEmpty()) {
            input_realName.setError("enter a valid email address")
            valid = false
        } else {
            input_realName.setError(null)
        }

        if (mobile.isEmpty() || mobile.length != 11) {
            input_mobile.setError("Enter Valid Mobile Number")
            valid = false
        } else {
            input_mobile.setError(null)
        }

        if (password.isEmpty() || password.length < 4 || password.length > 10) {
            input_password.setError("between 4 and 10 alphanumeric characters")
            valid = false
        } else {
            input_password.setError(null)
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length < 4 || reEnterPassword.length > 10 || reEnterPassword != password) {
            input_reEnterPassword.setError("Password Do not match")
            valid = false
        } else {
            input_reEnterPassword.setError(null)
        }

        return valid

    }

    //注册成功后调用的函数，用户信息存入greenDao,跳转界面
    override fun onSuccess(items: List<User>) {

        println(items[0].avatar)

        val user = items[0]

        saveUserToLocal(user, presenter.mUserDao!!)

    }

    //注册失败，给出一系列提示
    override fun onError(error: Throwable) {
        println(error)
    }


    //存入本地服务器
    override fun saveUserToLocal(item: User, userDao: GDUserDao){
        val db = userDao!!.queryBuilder()

        val list = db.list()

        //如果本地数据库为空，插入账户数据
        if (list.isEmpty() || list.size <= 0){

            val gdUser: GDUser = GDUser(item)

            userDao.insert(gdUser)

            val sUser = userDao.load(item.id)

            println(sUser.id)
        }
        //若不为空，则删掉再插入，保持数据库只存一个账户信息
        else {
            println(list[0].id)
            userDao.deleteByKey(list[0].id)

            val db = userDao!!.queryBuilder()
            val list = db.list()
            if (list.isEmpty() || list.size <= 0){

                val gdUser: GDUser = GDUser(item)
                userDao.insert(gdUser)

                val sUser = userDao.load(item.id)
                println(sUser.id)
            }


        }

    }



}