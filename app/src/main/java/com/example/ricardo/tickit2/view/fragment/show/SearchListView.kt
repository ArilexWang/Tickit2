package com.example.ricardo.tickit2.view.fragment.show

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.view.animation.DecelerateInterpolator
import android.widget.*
import com.example.ricardo.tickit2.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by yuhanyin on 12/20/17.
 */
class SearchListView : ListView, AbsListView.OnScrollListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemSelectedListener {

    /**
     * 状态
     */
    private var state = TOUCH_STATE_DONE

    /**
     * 标识查看更多状态
     */
    private var isMore = false

    private var scroller: Scroller? = null

    /**
     * 头部刷新的布局
     */
    private var viewHeader: View? = null
    /**
     * 尾部加载更多的布局
     */
    private var viewFooter: View? = null

    private var headerContentHeight: Int = 0 // 记录头部的高度

    /**
     * 头部显示下拉刷新等的控件
     */
    private var headContentLayout: RelativeLayout? = null
    private var tipsTextview: TextView? = null
    private var imgHeader: ImageView? = null
    private var progressBarHeader: ProgressBar? = null

    /**
     * Footer 刷新控件
     */
    private var txtFooter: TextView? = null
    private var progressBarFooter: ProgressBar? = null

    /**
     * 内容显示
     */
    private var layoutContent: RelativeLayout? = null

    /**
     * 头部高度
     */
    private var headHeight: Int = 0

    /**
     * 第一次记录的Y轴坐标
     */
    private var firstY: Float = 0.toFloat()

    /**
     * 最后一次记录的Y轴坐标
     */
    private var lastY: Float = 0.toFloat()

    /**
     * 总的item个数
     */
    private var totalItemCount: Int = 0

    /**
     * 是否要使用下拉刷新功能
     */
    var enablePullRefresh = true

    /**
     * 是否自动获取更多
     */
    private var isAutoFetchMore = false

    /**
     * 是否需要刷新功能，默认有刷新功能
     */
    private var isEnableRefresh = true


    private var onRefreshListener: OnRefreshListener? = null
    private var onLastItemVisibleListener: OnLastItemVisibleListener? = null

    /**
     * 获取头部视图
     *
     * @return View
     */
    private val headerView: View
        get() {
            if (viewHeader == null || tipsTextview == null) {
                viewHeader = LayoutInflater.from(context).inflate(R.layout.model_pull_listview_head, null)

                headContentLayout = viewHeader!!.findViewById<View>(R.id.head_contentLayout) as RelativeLayout
                tipsTextview = viewHeader!!.findViewById<View>(R.id.tvHead) as TextView
                imgHeader = viewHeader!!.findViewById<View>(R.id.ivHead) as ImageView
                progressBarHeader = viewHeader!!.findViewById<View>(R.id.pulldown_footer_loading) as ProgressBar

                layoutContent = viewHeader!!.findViewById<View>(R.id.layoutContent) as RelativeLayout

                val configuration = ViewConfiguration.get(context)
                mTouchSlop = configuration.scaledTouchSlop
            }

            if (!isEnableRefresh) {
                headContentLayout!!.visibility = View.GONE
            }

            return viewHeader as View
        }

    /**
     * 获取尾部视图
     *
     * @return View
     */
    private val footerView: View
        get() {
            if (viewFooter == null) {
                viewFooter = LayoutInflater.from(context).inflate(R.layout.model_pull_listview_footer, null)

                txtFooter = viewFooter!!.findViewById<View>(R.id.pulldown_footer_text) as TextView
                progressBarFooter = viewFooter!!.findViewById<View>(R.id.pulldown_footer_loading) as ProgressBar

                viewFooter!!.setOnClickListener {
                    if (!isMore) {
                        isMore = true

                        txtFooter!!.text = context.resources.getString(R.string.loading)
                        progressBarFooter!!.visibility = View.VISIBLE

                        if (onLastItemVisibleListener != null) {
                            onLastItemVisibleListener!!.onLastItemVisible()
                        }
                    }
                }
            }

            return viewFooter as View
        }


    private val TOUCH_STATE_REST = 0
    private val TOUCH_STATE_HORIZONTAL_SCROLLING = 1
    private val TOUCH_STATE_VERTICAL_SCROLLING = -1

    private val mTouchState = TOUCH_STATE_REST

    private var mTouchSlop: Int = 0
    private val mLastMotionX: Float = 0.toFloat()
    private val mLastMotionY: Float = 0.toFloat()


    /**
     * 条目是否填满整个屏幕
     */
    private val isFillScreenItem: Boolean
        get() {
            val firstVisiblePosition = firstVisiblePosition
            val lastVisiblePostion = lastVisiblePosition - footerViewsCount
            val visibleItemCount = lastVisiblePostion - firstVisiblePosition + 1
            val totalItemCount = count - footerViewsCount

            return visibleItemCount < totalItemCount
        }

    internal var sdf = SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault())

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        scroller = Scroller(context, DecelerateInterpolator())

        measureView(headerView)
        headHeight = headerView.measuredHeight
        headerView.setPadding(0, -1 * headHeight, 0, 0)
        headerView.invalidate()

        //      this.setBackgroundResource(R.color.stand_default_bg_color);

        //添加头部视图
        super.addHeaderView(headerView, null, false)

        setOnScrollListener(this)

        super.setOnItemClickListener(this)
        super.setOnItemLongClickListener(this)
        super.setOnItemSelectedListener(this)
    }

    /**
     * 添加头部的view 与下拉刷新不冲突
     *
     * @param header 视图
     */
    override fun addHeaderView(header: View?) {
        if (null == header) {
            return
        }

        if (!isEnableRefresh) {
            layoutContent!!.removeView(headerView)
            headHeight = headerView.measuredHeight
        }

        layoutContent!!.addView(header)

        measureView(header)
        headerContentHeight = header.measuredHeight

        headHeight += headerContentHeight

        headerView.setPadding(0, -1 * headHeight, 0, 0)
    }

    /**
     * 移除头部自定义添加内容
     */
    fun removeHeader() {
        layoutContent!!.removeAllViews()

        measureView(headerView) //防止多次添加导致高度计算失误
        headHeight = headerView.measuredHeight
    }

    override fun setOnItemClickListener(listener: AdapterView.OnItemClickListener?) {
        Companion.onItemClickListener = listener
    }

    override fun setOnItemLongClickListener(listener: AdapterView.OnItemLongClickListener) {
        this.onItemLongClickListener = listener
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (Companion.onItemClickListener == null) {
            return
        }

        Companion.onItemClickListener!!.onItemClick(parent, view, if (position > 0) position - 1 else 0, id)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        if (Companion.onItemSelectedListener == null) {
            return
        }

        Companion.onItemSelectedListener!!.onItemSelected(parent, view, if (position > 0) position - 1 else 0, id)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

    override fun onItemLongClick(parent: AdapterView<*>, view: View, position: Int, id: Long): Boolean {
        return onItemLongClickListener != null && onItemLongClickListener!!.onItemLongClick(parent, view, if (position > 0) position - 1 else 0, id)
    }

    override fun setOnItemSelectedListener(listener: AdapterView.OnItemSelectedListener?) {
        Companion.onItemSelectedListener = listener
    }

    override fun getItemAtPosition(position: Int): Any {
        return super.getItemAtPosition(position + 1)
    }

    override fun getItemIdAtPosition(position: Int): Long {
        return super.getItemIdAtPosition(position + 1)
    }

    /**
     * 设置是否可用下拉刷新
     *
     * @param enable boolean
     */
    fun pullRefreshEnable(enable: Boolean) {
        enablePullRefresh = enable
    }

    /**
     * 设置是否开启刷新功能，上拉下拉均包括。设为 false 的话，刷新功能均不可用，为普通列表
     * @param enable
     */
    fun setEnableRefresh(enable: Boolean) {
        isEnableRefresh = enable
    }

    /**
     * 是否开启自动获取更多 自动获取更多，将会隐藏footer，并在到达底部的时候自动刷新
     */
    fun setAutoFetchMore(enable: Boolean) {
        isAutoFetchMore = enable

        if (viewFooter == null) {
            return
        }

        txtFooter!!.text = context.resources.getString(R.string.pull_to_refresh_from_bottom_pull_label)
        progressBarFooter!!.visibility = View.GONE
    }


    /**
     * 触摸事件的处理
     */
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val action = ev.action

        cancelLongPress()
        when (action) {
            MotionEvent.ACTION_DOWN // 按下的时候
            -> {
                if (!scroller!!.isFinished) {
                    scroller!!.abortAnimation()
                }
                firstY = ev.rawY
                lastY = firstY
                Log.v(TAG, "在down时候记录当前位置: X: " + ev.x + " Y: " + ev.rawY)
            }
            MotionEvent.ACTION_MOVE // 手指正在移动的时候
            -> {
                //                    return super.onTouchEvent(ev);
                if (!enablePullRefresh)
                    //todo: if 中的break应该怎么用？
                    return false

                if (firstY == -1f) {
                    firstY = ev.rawY
                    lastY = firstY
                }

                val deltaY = ev.rawY - lastY
                lastY = ev.rawY

                if (firstVisiblePosition == 0 && (Math.abs(headerView.paddingTop) < headHeight || deltaY > 0)) {
                    val paddingTop = headerView.paddingTop

                    // change paddingTop with the movement of the finger
                    headerView.setPadding(0, (paddingTop + deltaY / OFFSET_RADIO).toInt(), 0, 0)

                    if (headerView.paddingTop > 0) {
                        if (state != TOUCH_STATE_RELEASE_TO_REFRESH && state != TOUCH_STATE_REFRESHING) {
                            state = TOUCH_STATE_RELEASE_TO_REFRESH
                            changeHeaderViewByState()
                        }
                    } else {
                        if (state != TOUCH_STATE_PULL_TO_REFRESH && state != TOUCH_STATE_REFRESHING) {
                            state = TOUCH_STATE_PULL_TO_REFRESH
                            changeHeaderViewByState()
                        }
                    }
                    return true
                } else if (lastVisiblePosition == totalItemCount - 1 && deltaY < 0) {
                    Log.i(TAG, "Footer" + (-deltaY / OFFSET_RADIO).toString())
                    return true
                }
            }

            MotionEvent.ACTION_UP // 手指抬起来的时候
            -> {
                if (firstVisiblePosition == 0 || firstVisiblePosition == 1) {
                    if (enablePullRefresh) {
                        val paddingTop = headerView.paddingTop

                        when (state) {
                            TOUCH_STATE_REFRESHING -> {
                                scroller!!.startScroll(0, paddingTop, 0, -paddingTop, SCROLL_DURATION)
                                state = TOUCH_STATE_DONE
                            }
                            TOUCH_STATE_PULL_TO_REFRESH -> {
                                if (paddingTop + headHeight > headHeight * 0.75) { //高过搜索框0.75的 那么显示搜索框
                                    scroller!!.startScroll(0, paddingTop, 0, -paddingTop, SCROLL_DURATION)
                                } else if (paddingTop + headHeight > 0 && paddingTop + headHeight < headHeight * 0.25) {
                                    if (firstY - lastY < 0) {//向下
                                        scroller!!.startScroll(0, paddingTop, 0, -(headHeight - headerContentHeight + paddingTop), SCROLL_DURATION)
                                    } else {//向上
                                        scroller!!.startScroll(0, paddingTop, 0, headHeight - headerContentHeight + paddingTop, SCROLL_DURATION)
                                    }
                                } else if (firstY - lastY < 0) {//向下
                                    scroller!!.startScroll(0, paddingTop, 0, -(headHeight - headerContentHeight + paddingTop))
                                } else {//向上
                                    scroller!!.startScroll(0, paddingTop, 0, -(headHeight + paddingTop))
                                }
                                state = TOUCH_STATE_DONE
                            }
                            TOUCH_STATE_RELEASE_TO_REFRESH -> {
                                state = TOUCH_STATE_REFRESHING  //将进度切换到正在刷新
                                changeHeaderViewByState()
                                scroller!!.startScroll(0, paddingTop, 0, -paddingTop, SCROLL_DURATION)

                                if (onRefreshListener != null) {
                                    onRefreshListener!!.onRefresh()
                                }
                            }

                            else -> {
                            }
                        }
                    }
                } else if (lastVisiblePosition == totalItemCount - 1) {
                    // 数量充满屏幕才触发
                    if (isAutoFetchMore && !isMore && onLastItemVisibleListener != null /*&& isFillScreenItem()*/ && viewFooter != null) {
                        txtFooter!!.text = context.resources.getString(R.string.loading)
                        progressBarFooter!!.visibility = View.VISIBLE

                        isMore = true
                        onLastItemVisibleListener!!.onLastItemVisible()
                        return true
                    }
                } else {
                    //这里只是为了方便计算
                    //不在第一个和最后一个的时候 隐藏头部
                    //不需要处理动画效果
                    headerView.setPadding(0, -1 * headHeight, 0, 0)
                }
                Log.i(TAG, "up:" + lastVisiblePosition + "   " + (totalItemCount - 1))
                firstY = -1f
                lastY = -1f // reset
            }
        }

        return super.onTouchEvent(ev)
    }

    override fun computeScroll() {
        if (scroller!!.computeScrollOffset()) {
            headerView.setPadding(0, scroller!!.currY, 0, 0)
            postInvalidate()
        }
    }

    /**
     * 自动刷新，显示头部
     * @param show boolean
     */
    fun showHeader(show: Boolean) {
        val paddingTop = headerView.paddingTop
        if (show) {
            imgHeader!!.visibility = View.GONE
            scroller!!.startScroll(0, paddingTop, 0, -paddingTop, SCROLL_DURATION)
        }
    }

    override fun onScroll(view: AbsListView, firstVisiableItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        this.totalItemCount = totalItemCount
        if (lastVisiblePosition == totalItemCount - 1) {
            // 数量充满屏幕才触发
            if (isAutoFetchMore && !isMore && onLastItemVisibleListener != null /*&& isFillScreenItem()*/ && viewFooter != null) {
                txtFooter!!.text = context.resources.getString(R.string.loading)
                progressBarFooter!!.visibility = View.VISIBLE

                isMore = true
                onLastItemVisibleListener!!.onLastItemVisible()
            }
        }
    }

    override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {

    }

    /**
     * 计算view的宽高
     *
     * @param child View
     */
    private fun measureView(child: View) {
        var lp: ViewGroup.LayoutParams? = child.layoutParams
        if (lp == null) {
            lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        val childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width)
        val lpHeight = lp.height
        val childHeightSpec: Int
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY)
        } else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        }
        child.measure(childWidthSpec, childHeightSpec)
    }

    fun onRefreshComplete() {
        state = TOUCH_STATE_DONE
        changeHeaderViewByState()

        val paddingTop = headerView.paddingTop
        scroller!!.startScroll(0, paddingTop, 0, -(headHeight + paddingTop), SCROLL_DURATION)
        invalidate()

        isMore = false

        if (viewFooter == null) {
            return
        }

        txtFooter!!.text = context.resources.getString(R.string.pull_to_refresh_from_bottom_pull_label)
        progressBarFooter!!.visibility = View.GONE

        //        getFooterView().setVisibility(View.GONE);
    }

    fun setLoadAll() {
        state = LOAD_ALL
        changeHeaderViewByState()
        pullRefreshEnable(false)
    }

    // 当状态改变时候，调用该方法，以更新界面
    private fun changeHeaderViewByState() {
        if (isEnableRefresh) {
            when (state) {
                TOUCH_STATE_RELEASE_TO_REFRESH -> {
                    Log.v(TAG, "当前状态，松开刷新")

                    tipsTextview!!.visibility = View.VISIBLE
                    tipsTextview!!.text = context.resources.getString(R.string.pull_to_refresh_from_bottom_release_label)
                    imgHeader!!.visibility = View.VISIBLE
                    progressBarHeader!!.visibility = View.GONE
                }
                TOUCH_STATE_PULL_TO_REFRESH -> {
                    Log.v(TAG, "当前状态，下拉刷新")

                    tipsTextview!!.visibility = View.VISIBLE
                    tipsTextview!!.text = context.resources.getString(R.string.pull_to_refresh_pull_label)

                    imgHeader!!.visibility = View.VISIBLE
                    progressBarHeader!!.visibility = View.GONE
                }
                TOUCH_STATE_REFRESHING -> {
                    Log.v(TAG, "当前状态,正在刷新...")

                    tipsTextview!!.text = context.resources.getString(R.string.pull_to_refresh_refreshing_label)
                    imgHeader!!.visibility = View.GONE
                    progressBarHeader!!.visibility = View.VISIBLE
                }
                TOUCH_STATE_DONE -> {
                    Log.v(TAG, "当前状态，done")

                    tipsTextview!!.text = context.resources.getString(R.string.pull_to_refresh_pull_label)
                    imgHeader!!.visibility = View.VISIBLE
                    progressBarHeader!!.visibility = View.GONE
                }
                LOAD_ALL -> {
                    Log.v(TAG, "当前状态,load all")

                    tipsTextview!!.text = context.resources.getString(R.string.load_all)
                    imgHeader!!.visibility = View.VISIBLE
                    progressBarFooter!!.visibility = View.GONE
                }
            }
        }
    }

    /**
     * 下拉刷新监听事件
     *
     * @param listener OnRefreshListener
     */
    fun setOnRefreshListener(listener: OnRefreshListener) {
        this.onRefreshListener = listener
    }

    /**
     * 获取更多监听事件
     *
     * @param listener OnLastItemVisibleListener
     */
    fun setOnLastItemVisibleListener(listener: OnLastItemVisibleListener?) {
        this.onLastItemVisibleListener = listener
        if (listener == null) {
            removeFooterView(footerView)
            viewFooter = null
            txtFooter = null
            progressBarFooter = null
        } else {
            if (isEnableRefresh && viewFooter == null) {
                addFooterView(footerView)
            }
        }

        if (viewFooter == null) {
            return
        }

        txtFooter!!.text = context.resources.getString(R.string.pull_to_refresh_from_bottom_pull_label)
        progressBarFooter!!.visibility = View.GONE
    }

    /**
     * 刷新事件接口
     */
    interface OnRefreshListener {
        /**
         * 刷新事件接口 这里要注意的是获取更多完 要关闭 刷新的进度条onRefreshComplete()
         */
        fun onRefresh()
    }

    /**
     * 获取更多事件接口
     */
    interface OnLastItemVisibleListener {
        /**
         * 刷新事件接口 这里要注意的是获取更多完 要关闭 更多的进度条 onRefreshComplete()
         */
        fun onLastItemVisible()
    }

    companion object {

        private val TAG = "listview"

        private val SCROLL_DURATION = 200 // scroll back duration

        private val OFFSET_RADIO = 2.5f // support iOS like pull  2.5f

        /**
         * 完成
         */
        private val TOUCH_STATE_DONE = 0
        /**
         * 松开更新
         */
        private val TOUCH_STATE_RELEASE_TO_REFRESH = 1
        /**
         * 下拉更新
         */
        private val TOUCH_STATE_PULL_TO_REFRESH = 2
        /**
         * 正在刷新
         */
        private val TOUCH_STATE_REFRESHING = 3
        /**
         * 已加载全部
         */
        private val LOAD_ALL = 4
        private var onItemClickListener: AdapterView.OnItemClickListener? = null
        private var onItemSelectedListener: AdapterView.OnItemSelectedListener? = null
        private var onItemLongClickListener: OnItemLongClickListener? = null
    }
}
