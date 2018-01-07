package com.example.ricardo.tickit2.extensions

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.utils.Auth
import com.example.ricardo.tickit2.data.network.utils.Config
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ricardo on 2017/12/17.
 */

fun BasePresenter.postAvatar(path: String): String?{
    val uploadManager = UploadManager()
    val sdf = SimpleDateFormat("yyyyMMddHHmmss")
    val key = "icon_" + sdf.format(Date())
    val picPath = path

    var remotePath: String? = null

    uploadManager.put(picPath, key, Auth
            .create(Config.ACCESS_KEY , Config.SECRET_KEY).uploadToken(Config.BUCKET_NAME), UpCompletionHandler { key, info, res ->
        if (info.isOK) {
            val headpicPath = Config.TEST_DOMAIN + key
            remotePath = headpicPath
        }
        else{
            remotePath = null
        }

    }, null)

    return remotePath

}

fun BasePresenter.getLocalUser(mUserDao: GDUserDao): User?{
    val db = mUserDao!!.queryBuilder()

    val list = db.list()

    if (!list.isEmpty()){
        val user: User = User(list[0])

        return user

    }

    return null

}
