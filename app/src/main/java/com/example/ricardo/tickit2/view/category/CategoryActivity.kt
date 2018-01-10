package com.example.ricardo.tickit2.view.category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.data.ODNR_NUMBER
import com.example.ricardo.tickit2.data.PWXQR_NUMBER
import com.example.ricardo.tickit2.data.model.Show
import com.example.ricardo.tickit2.data.network.repository.ShowRepository
import com.example.ricardo.tickit2.view.category.fragment.*
import com.github.florent37.materialviewpager.MaterialViewPager
import com.github.florent37.materialviewpager.header.HeaderDesign
import kotlinx.android.synthetic.main.activity_category.*

/**
 * Created by yuhanyin on 1/3/18.
 */
class CategoryActivity : DrawerActivity(), CategoryView {

    override val presenter: CategoryPresenter = CategoryPresenter(ShowRepository.get(), this)

    var pwxqrList: MutableList<Show> = mutableListOf()
    var activityList: MutableList<Show> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        presenter.start()

        title = "分类"
//        val toolbar = materialViewPager!!.toolbar
        val toolbar = materialViewPager.toolbar
        if (toolbar != null) {
//            setSupportActionBar(toolbar)
        }

    }

    override fun onShowError(error: Throwable) {

    }

    override fun onShowSuccess(items: List<Show>) {
        for (item in items){
            if (item.category == PWXQR_NUMBER){
                pwxqrList.add(item)
            } else if (item.category == ODNR_NUMBER){
                activityList.add(item)
            }
        }

        materialViewPager!!.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {

                println(pwxqrList.count())

                when (position % 4) {
                    0 ->
                        return CategoryShowFragment.newInstance();
                    1 ->
                        return CategoryActivityFragment.newInstance(activityList);
                    2 ->
                        return CategoryXQRFragment.newInstance(pwxqrList);
                    else -> return CategoryShowFragment.newInstance()
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
                        "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg")
                1 -> return@Listener HeaderDesign.fromColorResAndUrl(
                        R.color.blue,
                        "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg")
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

    }
}