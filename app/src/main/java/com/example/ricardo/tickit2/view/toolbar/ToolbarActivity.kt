package com.example.ricardo.tickit2.view.toolbar

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.view.common.BaseActivity

/**
 * Created by yuhanyin on 12/19/17.
 */
class ToolbarActivity : BaseActivity() {
    override val presenter: BasePresenter
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    private var mHelper: ToolbarHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)

        mHelper = ToolbarHelper(this, layoutResID)
        val toolbar = mHelper!!.getToolbar()
        setContentView(mHelper!!.getContentView())
        setSupportActionBar(toolbar)

        setCustomToolbar(toolbar)
    }

    private fun setCustomToolbar(toolbar: Toolbar) {

    }
}