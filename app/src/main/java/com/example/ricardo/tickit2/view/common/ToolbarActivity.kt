package com.example.ricardo.tickit2.view.common

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import com.example.ricardo.tickit2.base.BasePresenter

/**
 * Created by yuhanyin on 12/17/17.
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

        setCustomToolbar(toolbar!!)
    }

    protected fun setCustomToolbar(toolbar: Toolbar) {

    }
}
