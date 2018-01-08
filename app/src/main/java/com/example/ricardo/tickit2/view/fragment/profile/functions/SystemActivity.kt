package com.example.ricardo.tickit2.view.fragment.profile.functions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.views.ViewsActivity
import kotlinx.android.synthetic.main.activity_system.*

/**
 * Created by yuhanyin on 1/7/18.
 */
class SystemActivity : BaseActivity() {
    override val presenter: BasePresenter
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system)

        systemBack.setOnClickListener {
            val intent = Intent()
            val bundle = Bundle()
            bundle.putString("view","4")
            intent.setClass(this@SystemActivity,ViewsActivity::class.java)
            intent.putExtra("systemflag", 2)
            intent.putExtras(bundle)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }
}