package com.example.ricardo.tickit2.view.photo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ImageView

import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.extensions.loadDaoSession
import com.example.ricardo.tickit2.extensions.postAvatar
import com.example.ricardo.tickit2.view.common.BaseActivity

import java.io.File

/**
 * Created by Ricardo on 2017/12/17.
 */

class PhotoChoseActivity : BaseActivity() {

    override val presenter by lazy { PhotoChosePresenter() }

    private var select_photo: Button? = null
    private var iv_photo: ImageView? = null

    private var photoPath: String? = null

    private val IMAGE_FILE_NAME = System.currentTimeMillis().toString() + "crecirheader.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        fromImg()

        val gdUser = loadDaoSession().gdUserDao


        select_photo = findViewById<View>(R.id.select_photo) as Button
        iv_photo = findViewById<View>(R.id.iv_photo) as ImageView
        select_photo!!.setOnClickListener { selectBtnClick() }
    }

    fun fromImg() {
        // 使用意图直接调用手机相册
        val intent = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        // 打开手机相册,设置请求码
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    fun selectBtnClick(){

        println(photoPath)

        println(presenter.postAvatar(photoPath!!))



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        } else {
            when (requestCode) {
                IMAGE_REQUEST_CODE -> resizeImage(data!!.data)

                RESIZE_REQUEST_CODE -> if (data != null) {
                    showResizeImage(data)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun resizeImage(uri: Uri?) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        intent.putExtra("crop", "true")
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        intent.putExtra("outputX", 150)
        intent.putExtra("outputY", 150)
        intent.putExtra("return-data", true)
        startActivityForResult(intent, RESIZE_REQUEST_CODE)
    }

    private fun showResizeImage(data: Intent) {
        val extras = data.extras
        if (extras != null) {
            val photo = extras.getParcelable<Bitmap>("data")
            //            String path = getExternalCacheDir().getAbsolutePath() + File.separator + IMAGE_FILE_NAME;
            val path = filesDir.path + File.separator + IMAGE_FILE_NAME

            photoPath = path

            ImageUtils.saveImage(photo, path)
            BitmapDrawable()
            val drawable = BitmapDrawable(photo)
            iv_photo!!.setImageDrawable(drawable)
        }
    }

    companion object {

        private val IMAGE_REQUEST_CODE = 0
        private val RESIZE_REQUEST_CODE = 2
    }
}
