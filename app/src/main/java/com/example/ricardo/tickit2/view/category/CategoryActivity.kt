package com.example.ricardo.tickit2.view.category

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.widget.Toast
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.category.fragment.DrawerActivity
import com.example.ricardo.tickit2.view.category.fragment.RecyclerViewFragment
import com.example.ricardo.tickit2.view.views.ViewsActivity
import com.github.florent37.materialviewpager.MaterialViewPager
import com.github.florent37.materialviewpager.header.HeaderDesign
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.header_logo.*

/**
 * Created by yuhanyin on 1/3/18.
 */
class CategoryActivity : DrawerActivity() {

//    @BindView(R.id.materialViewPager)
//    internal var mViewPager: MaterialViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_category)
        title = ""
//        val toolbar = materialViewPager!!.toolbar
//        val toolbar = materialViewPager.toolbar
//        if (toolbar != null) {
//            setSupportActionBar(toolbar)
//        }
        leftDrawer.setOnClickListener {
            val intent = Intent()
            intent.setClass(this@CategoryActivity, ViewsActivity::class.java)
            startActivity(intent)
        }


        materialViewPager!!.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                when (position % 4) {
                //case 0:
                //    return RecyclerViewFragment.newInstance();
                //case 1:
                //    return RecyclerViewFragment.newInstance();
                //case 2:
                //    return WebViewFragment.newInstance();
                    else -> return RecyclerViewFragment.newInstance()
                }
            }

            override fun getCount(): Int {
                return 4
            }

            override fun getPageTitle(position: Int): CharSequence {
                when (position % 4) {
                    0 -> return "演出票"
                    1 -> return "活动票"
                    2 -> return "票务新奇日"
                    3 -> return "其他"
                }
                return ""
            }
        }

        materialViewPager!!.setMaterialViewPagerListener(MaterialViewPager.Listener { page ->
            when (page) {
                0 -> return@Listener HeaderDesign.fromColorResAndUrl(
                        R.color.green,
                        "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg")
                1 -> return@Listener HeaderDesign.fromColorResAndUrl(
                        R.color.blue,
                        "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg")
                2 -> return@Listener HeaderDesign.fromColorResAndUrl(
                        R.color.btn_bg_pressed_color,
                        "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg")
                3 -> return@Listener HeaderDesign.fromColorResAndUrl(
                        R.color.grey,
                        "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg")
            }

            //execute others actions if needed (ex : modify your header logo)

            null
        })

        materialViewPager!!.viewPager.offscreenPageLimit = materialViewPager!!.viewPager.adapter.count
        materialViewPager!!.pagerTitleStrip.setViewPager(materialViewPager!!.viewPager)

//        val logo = findViewById(R.id.logoWhite)
//        if (logoWhite != null) {
//            logoWhite!!.setOnClickListener{
//                materialViewPager!!.notifyHeaderChanged()
//                Toast.makeText(applicationContext, "Yes, the title is clickable", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}