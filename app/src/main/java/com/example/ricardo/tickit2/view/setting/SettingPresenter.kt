package com.example.ricardo.tickit2.view.setting

import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.utils.Auth
import com.example.ricardo.tickit2.data.network.utils.Config
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import java.text.SimpleDateFormat
import java.util.*
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager


/**
 * Created by Ricardo on 2017/12/2.
 */
class SettingPresenter (val view: SettingView): SettingContract.Presenter{


    var userDao:GDUserDao? = null

    override fun start() {

    }

    override fun postAvatar(path: String){
        val uploadManager = UploadManager()
        val sdf = SimpleDateFormat("yyyyMMddHHmmss")
        val key = "icon_" + sdf.format(Date())
        val picPath = path

        uploadManager.put(picPath, key, Auth
                .create(Config.ACCESS_KEY , Config.SECRET_KEY).uploadToken(Config.BUCKET_NAME), UpCompletionHandler { key, info, res ->
            if (info.isOK) {
                val headpicPath = Config.TEST_DOMAIN + key
                println(headpicPath)
            }
            else{

            }

        }, null)
    }

    override fun updateUserInfo(user: User) {

    }


    override fun onViewDestroyed() {

    }

    companion object {

    }

}