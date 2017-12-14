package com.example.ricardo.tickit2.view.views

import android.content.Context
import android.support.v4.view.ViewPager

import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by yuhanyin on 2017/12/8.
 */

class APSTSViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {
    private var mNoFocus = false //if true, keep View don't move

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (mNoFocus) {
            false
        } else super.onInterceptTouchEvent(event)
    }

    fun setNoFocus(b: Boolean) {
        mNoFocus = b
    }
}