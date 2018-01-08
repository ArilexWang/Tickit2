package com.example.ricardo.tickit2.view.common

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.HorizontalScrollView



/**
 * Created by Ricardo on 2018/1/4.
 */

class ShowHorizontalScrollview(
        internal var context: Context,
        attrs: AttributeSet) : HorizontalScrollView(context, attrs) {

    internal var prevIndex = 0

    init {
        this.isSmoothScrollingEnabled = true
    }

    fun setAdapter(context: Context, mAdapter: ShowScrollViewAdapter) {

        try {
            fillViewWithAdapter(mAdapter)
        } catch (e: ZeroChildException) {

            e.printStackTrace()
        }

    }
    @Throws(ZeroChildException::class)
    private fun fillViewWithAdapter(mAdapter: ShowScrollViewAdapter?) {
        if (childCount == 0) {
            throw ZeroChildException(
                    "CenterLockHorizontalScrollView must have one child")
        }
        if (childCount == 0 || mAdapter == null)
            return

        val parent = getChildAt(0) as ViewGroup

        parent.removeAllViews()

        for (i in 0 until mAdapter!!.getCount()) {
            parent.addView(mAdapter!!.getView(i, null, parent))
        }
    }


}