package com.example.ricardo.tickit2.view.views

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.RelativeLayout
import android.widget.Toast
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.fragment.cart.CartFragment
import com.example.ricardo.tickit2.view.fragment.home.HomeFragment
import com.example.ricardo.tickit2.view.fragment.profile.ProfileFragment
import com.example.ricardo.tickit2.view.fragment.show.ShowFragment
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip
import com.lhh.apst.library.Margins

import kotlinx.android.synthetic.main.activity_views.*

class ViewsActivity :AppCompatActivity(),ViewPager.OnPageChangeListener{

    private var mHomeFragment: HomeFragment? = null
    public var mCategoryFragment: ShowFragment? =null
    public var mCartFragment: CartFragment? = null
    public var mProfileFragment: ProfileFragment? = null

    private var mSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views)
        init()
    }

    private fun init() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        ivCenterBtn.setOnClickListener {
            Toast.makeText(this@ViewsActivity, "Center Btn is Clicked.", Toast.LENGTH_SHORT).show()
        }

        mSize = resources.getDimensionPixelSize(R.dimen.weibo_tab_size)

        /*
        todo：VIEW_SIZE 默认是1------要改成4
         */
        vp_main.offscreenPageLimit = VIEW_SIZE

        val adapter = FragmentAdapter(supportFragmentManager)
        vp_main.adapter = FragmentAdapter(supportFragmentManager)

        adapter.notifyDataSetChanged()

        tabs.setViewPager(vp_main)

        tabs.setOnPageChangeListener(this)

        vp_main.currentItem = VIEW_FIRST

        //example of how to show dot on icons
        //tabs.showDot(VIEW_FOURTH, "99+")

    }



    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    @Suppress("UNCHECKED_CAST")
    inner class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm), AdvancedPagerSlidingTabStrip.IconTabProvider, AdvancedPagerSlidingTabStrip.LayoutProvider, AdvancedPagerSlidingTabStrip.TipsProvider {


        override fun getItem(position: Int): Fragment? {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> {
                        if (null == mHomeFragment) mHomeFragment = HomeFragment.instance()
                        return mHomeFragment
                    }

                    VIEW_SECOND -> {
                        if (null == mCategoryFragment) mCategoryFragment = ShowFragment()
                        return mCategoryFragment
                    }

                    VIEW_THIRD -> {
                        if (null == mCartFragment)
                            mCartFragment = CartFragment.instance()
                        return mCartFragment
                    }

                    VIEW_FOURTH -> {
                        if (null == mProfileFragment)
                            mProfileFragment = ProfileFragment.instance()
                        return mProfileFragment
                    }
                    else -> {

                    }
                }
            }
            return null
        }

        override fun getCount(): Int {
            return VIEW_SIZE
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return "Home"
                    VIEW_SECOND -> return "Search"
                    VIEW_THIRD -> return "Cart"
                    VIEW_FOURTH -> return "Profile"
                    else -> {

                    }
                }
            }
            return null
        }

        override fun getPageWeight(position: Int): Float {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return 1.0f
                    VIEW_SECOND -> return 1.0f
                    VIEW_THIRD -> return 1.0f
                    VIEW_FOURTH -> return 1.0f
                    else -> {
                    }
                }
            }
            return 1.0f
        }

        override fun getPageRule(position: Int): IntArray {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return intArrayOf(RelativeLayout.ALIGN_PARENT_LEFT)
                    VIEW_SECOND -> return intArrayOf(RelativeLayout.ALIGN_PARENT_LEFT)
                    VIEW_THIRD -> return intArrayOf(RelativeLayout.ALIGN_PARENT_RIGHT)
                    VIEW_FOURTH -> return intArrayOf(RelativeLayout.ALIGN_PARENT_RIGHT)
                    else -> {
                    }
                }
            }
            return IntArray(0)
        }

        override fun getPageMargins(position: Int): Margins? {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return Margins(resources.getDimensionPixelSize(R.dimen.home_bar_icon_margins), 0, 0, 0)
                    VIEW_SECOND -> return null
                    VIEW_THIRD -> return null
                    VIEW_FOURTH -> return Margins(0, 0, resources.getDimensionPixelSize(R.dimen.home_bar_icon_margins), 0)
                    else -> {
                    }
                }
            }
            return null
        }



         override fun <T : Any?> getPageSelectIcon(position: Int): T {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return R.mipmap.tabbar_home_select as T
                    VIEW_SECOND -> return R.mipmap.tabbar_search_select as T
                    VIEW_THIRD -> return R.mipmap.tabbar_cart_select as T
                    VIEW_FOURTH -> return R.mipmap.tabbar_profile_select as T
                    else -> {
                    }
                }
            }
            return 0 as T

        }

        override fun <T : Any?> getPageIcon(position: Int): T {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return R.mipmap.tabbar_home as T
                    VIEW_SECOND -> return R.mipmap.tabbar_search as T
                    VIEW_THIRD -> return R.mipmap.tabbar_cart as T
                    VIEW_FOURTH -> return R.mipmap.tabbar_profile as T
                    else -> {
                    }
                }
            }
            return 0 as T
        }


        override fun getPageIconBounds(position: Int): Rect {
            return Rect(0, 0, mSize, mSize)
        }

        override fun getTipsRule(position: Int): IntArray {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return intArrayOf(RelativeLayout.ALIGN_PARENT_LEFT)
                    VIEW_SECOND -> return intArrayOf(RelativeLayout.ALIGN_PARENT_LEFT)
                    VIEW_THIRD -> return intArrayOf(RelativeLayout.ALIGN_PARENT_RIGHT)
                    VIEW_FOURTH -> return intArrayOf(RelativeLayout.ALIGN_PARENT_RIGHT)
                    else -> {
                    }
                }
            }
            return IntArray(0)
        }

        override fun getTipsMargins(position: Int): Margins? {
            if (position in 0..(VIEW_SIZE - 1)) {
                when (position) {
                    VIEW_FIRST -> return Margins(4 * resources.getDimensionPixelSize(R.dimen.psts_dot_m_right), 0, 0, 0)
                    else -> {
                    }
                }
            }
            return null
        }

        override fun getTipsDrawable(position: Int): Drawable? {
            return null
        }
    }


    companion object {

        private val VIEW_FIRST = 0
        private val VIEW_SECOND = 1
        private val VIEW_THIRD = 2
        private val VIEW_FOURTH = 3

        private val VIEW_SIZE = 4
    }
}


