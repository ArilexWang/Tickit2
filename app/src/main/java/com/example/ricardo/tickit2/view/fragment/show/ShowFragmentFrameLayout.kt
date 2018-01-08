package com.example.ricardo.tickit2.view.fragment.show

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by yuhanyin on 1/8/18.
 */
class ShowFragmentFrameLayout  @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet?=null,
        defStyleAttr: Int=0

) : FrameLayout(context, attrs, defStyleAttr){
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}