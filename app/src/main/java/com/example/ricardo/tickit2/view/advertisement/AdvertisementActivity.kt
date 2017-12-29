package com.example.ricardo.tickit2.view.advertisement

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.view.common.BaseActivity
import kotlinx.android.synthetic.main.activity_advertisement.*
import android.content.DialogInterface



/**
 * Created by Ricardo on 2017/12/25.
 */
class AdvertisementActivity:BaseActivity() {
    override val presenter by lazy { AdvertisementPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertisement)

        val webSettings = wb.settings

        wb.settings.javaScriptEnabled
        wb.requestFocus()

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式



        val intent:Intent = getIntent()
        val url = intent.getStringExtra("url")

        wb.loadUrl(url)

        wb.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })

        participateBtn.setOnClickListener{ participateBtnClick() }


    }

    val items = arrayOf("a","b")
    var choses = arrayListOf<Boolean>(false,false)

    fun participateBtnClick(){
        val dialog = AlertDialog.Builder(this)
                .setTitle("选择票价")
                .setIcon(R.drawable.shake)
                .setNegativeButton("取消", object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, p1: Int) {

                    }
                })
                .setPositiveButton("立即参与", object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        positiveClick()
                    }
                })
                .setSingleChoiceItems(items,-1,object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        println(items[p1])
                        choses[p1] = true
                    }
                }).create()
        dialog.show()
    }

    fun positiveClick(){
        println(choses[0])
    }
}