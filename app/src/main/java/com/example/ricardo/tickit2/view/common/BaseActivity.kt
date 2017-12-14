package com.example.ricardo.tickit2.view.common

import android.support.v7.app.AppCompatActivity
import com.example.ricardo.tickit2.base.BasePresenter

/**
 * Created by Ricardo on 2017/11/10.
 */

abstract class BaseActivity : AppCompatActivity() {
    abstract val presenter: BasePresenter
}