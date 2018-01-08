package com.example.ricardo.tickit2.view.admin.set

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by Ricardo on 2018/1/6.
 */

class BannerFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet?=null,
        defStyleAttr: Int=0

) : FrameLayout(context, attrs, defStyleAttr){
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}