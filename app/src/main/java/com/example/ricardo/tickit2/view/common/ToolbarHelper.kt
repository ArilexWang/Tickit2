package com.example.ricardo.tickit2.view.common

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.ricardo.tickit2.R

/**
 * Created by yuhanyin on 12/17/17.
 */
class ToolbarHelper(private val mContext: Context, @LayoutRes layoutID: Int) {
    var mContentView: FrameLayout? = null
        private set
    private val mInflater: LayoutInflater
    private var mUserView: View? = null
    var mToolbar: Toolbar? = null
        private set
    init {
        mInflater = LayoutInflater.from(mContext)
        //初始化整个内容
        initContentView()
        //初始化自定义布局
        initUserView(layoutID)
        //初始化Toolbar
        initToolbar()
    }

    private fun initUserView(layoutID: Int) {
        mUserView = mInflater.inflate(layoutID, null)
        val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        mUserView!!.layoutParams = params
    }

    private fun initToolbar() {
        val view = mInflater.inflate(R.layout.layout_toolbar, mContentView)
        mToolbar = view.findViewById(R.id.toolbar)
    }

    private fun initContentView() {
        mContentView = FrameLayout(mContext)
        val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        mContentView!!.layoutParams = params
    }

    fun getToolbar(): Toolbar {
        return mToolbar!!
    }

    fun getContentView(): FrameLayout {
        return mContentView!!
    }
}
