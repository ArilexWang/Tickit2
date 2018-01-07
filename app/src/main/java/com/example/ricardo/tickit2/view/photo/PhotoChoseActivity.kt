package com.example.ricardo.tickit2.view.photo

import android.app.Activity
import android.content.Context
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
import com.example.ricardo.tickit2.data.model.BannerPicture
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.repository.UserRepository
import com.example.ricardo.tickit2.extensions.*
import com.example.ricardo.tickit2.view.admin.bannerSetting.BannerSettingActivity
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.example.ricardo.tickit2.view.profile.ProfileInfoActivity
import com.example.ricardo.tickit2.view.setting.SettingActivity
import com.youth.banner.Banner

import java.io.File

/**
 * Created by Ricardo on 2017/12/17.
 */

class PhotoChoseActivity : BaseActivity(),PhotoChoseView {

    override val presenter by lazy { PhotoChosePresenter(this, UserRepository.get()) }

    val from: String by lazy { intent.getStringExtra(INTENT) }
    val banner: BannerPicture by extra(BANNER_ARG)

    private var select_photo: Button? = null
    private var iv_photo: ImageView? = null

    private var photoPath: String? = null

    private val IMAGE_FILE_NAME = System.currentTimeMillis().toString() + "crecirheader.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        fromImg()


        val gdUser = loadDaoSession().gdUserDao

        presenter.userDao = gdUser

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
        presenter.postAvatar(photoPath!!)

    }

    //图片上传七牛云成功
    override fun postAvatarSuccess(path: String?) {
        if (from == AVATAR){
            presenter.updateUserInfo(path!!)
        } else {
            banner.picPath = path!!
            BannerSettingActivity.startFromAdd(this,banner,"ADD")
        }
    }

    //图片上传七牛云失败
    override fun postAvatarError(path: String?) {
        println("Upload Error")
    }

    //更新服务器信息成功
    override fun onSuccess(items: List<User>) {
        val user = items[0]
        saveUserToLocal(user, presenter.userDao!!)
        val intent = Intent()
        intent.setClass(this@PhotoChoseActivity, ProfileInfoActivity::class.java)
        startActivity(intent)

    }

    //更新服务器信息失败
    override fun onError(error: Throwable) {
        println(error)
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
        private val AVATAR = "avatar"

        private const val INTENT = "FROM"
        private const val BANNER_INTENT = "FROM_BANNER"
        private const val BANNER_ARG = "BANNER_KEY"

        fun getInent(context: Context, from: String) = context
                .getIntent<PhotoChoseActivity>()
                .apply {
                    putExtra(INTENT, from)
                }

        fun getIntent(context: Context,from: String, bannerPicture: BannerPicture) = context
                .getIntent<PhotoChoseActivity>()
                .apply {
                    putExtra(INTENT,from)
                    putExtra(BANNER_ARG, bannerPicture)
                }


        fun start(context: Context, from: String){
            val intent = getInent(context,from)
            context.startActivity(intent)
        }

        fun startFromBanner(context: Context, from: String, bannerPicture: BannerPicture){
            val intent = getIntent(context,from,bannerPicture)
            context.startActivity(intent)
        }

    }
}
