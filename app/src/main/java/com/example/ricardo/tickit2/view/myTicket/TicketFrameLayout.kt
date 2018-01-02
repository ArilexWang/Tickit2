package com.example.ricardo.tickit2.view.myTicket

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by Ricardo on 2017/12/29.
 */

class TicketFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet?=null,
        defStyleAttr: Int=0

) : FrameLayout(context, attrs, defStyleAttr){
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}