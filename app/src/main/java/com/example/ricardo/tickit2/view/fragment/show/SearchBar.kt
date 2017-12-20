package com.example.ricardo.tickit2.view.fragment.show

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Scroller
import android.widget.TextView
import com.example.ricardo.tickit2.R
import java.util.*

/**
 * Created by yuhanyin on 12/20/17.
 */
class SearchBar : LinearLayout {

    private var state = STATE_VIEW

    private var distance: Int = 0  //计算动画位移
    private var screenWidth: Int = 0 //屏幕的宽度

    private var mScroller: Scroller? = null

    /**
     * 取得搜索文本框
     */
    var searchText: TextView? = null
        private set
    private var layout: LinearLayout? = null


    /**
     * 取消键点击事件处理
     */
    private val cancelClickListener = OnClickListener {
        if (state == STATE_EDIT) {
            state = STATE_VIEW
            changeState()
        }
    }

    /**
     * EditText Touch事件处理
     */
    private val editTextOnTouchListener = OnTouchListener { v, event ->
        if (state == STATE_VIEW) {
            state = STATE_EDIT
            changeState()
        }
        false
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    /***
     * 界面初始化
     */
    private fun init() {
        mScroller = Scroller(context, DecelerateInterpolator())

        //计算屏幕的宽度
        var dm = DisplayMetrics()
        dm = resources.displayMetrics
        screenWidth = dm.widthPixels

        val view = View.inflate(context, R.layout.bt_header_recommend_search, this)
        layout = view.findViewById<View>(R.id.recommend_search_layout) as LinearLayout
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        view.layoutParams = layoutParams

        //        measureView(layout);
        distance = layout!!.measuredWidth

        try {
            val params = layout!!.layoutParams as LinearLayout.LayoutParams
            params.setMargins(20, 20, 20, 20)
            layout!!.layoutParams = params
        } catch (e: Exception) {
            e.printStackTrace()
        }

        searchText = findViewById<View>(R.id.drawer_search) as TextView
        searchText!!.setOnTouchListener(editTextOnTouchListener)
    }

    /**
     * 计算view的宽高
     *
     * @param child
     */
    private fun measureView(child: View) {
        var lp: ViewGroup.LayoutParams? = child.layoutParams
        if (lp == null) {
            lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        val childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, lp.width)
        val lpHeight = lp.height
        val childHeightSpec: Int
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY)
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        }
        child.measure(childWidthSpec, childHeightSpec)
    }

    /***
     * 设置搜索框是否可以编辑
     *
     * @param isEditable
     */
    fun setTextEditable(isEditable: Boolean) {
        if (isEditable) {
            searchText!!.isFocusableInTouchMode = true
            searchText!!.isFocusable = true
            searchText!!.requestFocus()
        } else {
            searchText!!.clearFocus()
            searchText!!.isFocusable = false
        }

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val imm = searchText!!.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }, DURATION.toLong())
    }

    fun setState(state: Int) {
        this.state = state
    }

    /**
     * 设置搜索条的状态<br></br>
     * 浏览状态 STATE_VIEW 只显示搜索条 同时失去焦点<br></br>
     * 编辑状态 STATE_EDIT 显示搜索条和取消按钮 获取焦点
     *
     */
    private fun changeState() {
        when (state) {
            STATE_VIEW -> {
                searchText!!.text = ""
                setTextEditable(false)

                mScroller!!.startScroll(layout!!.left, 0, distance, 0, DURATION)
                invalidate()
            }
            STATE_EDIT -> {
                setTextEditable(true)

                mScroller!!.startScroll(layout!!.left, 0, -distance, 0, DURATION)
                invalidate()
            }
        }
    }

    override fun computeScroll() {
        if (mScroller!!.computeScrollOffset()) {
            Log.i("", mScroller!!.currX.toString())

            val params = layout!!.layoutParams as LinearLayout.LayoutParams
            params.setMargins(0, 0, screenWidth - mScroller!!.currX - distance, 0)
            layout!!.layoutParams = params

            postInvalidate()
        }
        super.computeScroll()
    }

    companion object {

        private val DURATION = 300

        val STATE_VIEW = 1
        val STATE_EDIT = 2
    }
}
