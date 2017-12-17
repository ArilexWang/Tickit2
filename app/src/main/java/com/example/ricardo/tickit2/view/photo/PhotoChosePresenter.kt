package com.example.ricardo.tickit2.view.photo

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.entity.GDUser
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

class PhotoChosePresenter(val view: PhotoChoseView): PhotoChoseContract.Presenter{
    var userDao: GDUserDao? = null

    override fun start() {
    }


    override fun postAvatar(path: String){
        val uploadManager = UploadManager()
        val sdf = SimpleDateFormat("yyyyMMddHHmmss")
        val key = "icon_" + sdf.format(Date())
        val picPath = path

        var remotePath: String? = null

        uploadManager.put(picPath, key, Auth
                .create(Config.ACCESS_KEY , Config.SECRET_KEY).uploadToken(Config.BUCKET_NAME), UpCompletionHandler { key, info, res ->
            if (info.isOK) {
                val headpicPath = Config.TEST_DOMAIN + "/" + key

                view.postAvatarSuccess(headpicPath)

            }
            else{
                view.postAvatarError(null)
            }

        }, null)


    }
    override fun updateUserInfo(avatarPath: String) {
        println(avatarPath)

        val db = userDao!!.queryBuilder()

        val list = db.list()

        val gdUser = userDao!!.load(list[0].id)

        var nUser = User(gdUser)

        nUser.avatar = avatarPath

        val newGDUser = GDUser(nUser)

        userDao!!.update(newGDUser)

        print(userDao!!.load(list[0].id).avatar)

    }

    override fun onViewDestroyed() {

    }

}