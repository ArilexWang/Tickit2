package com.example.ricardo.tickit2.view.photo

import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.entity.GDUser
import com.example.ricardo.tickit2.data.model.User
import com.example.ricardo.tickit2.data.network.repository.UserRepository
import com.example.ricardo.tickit2.data.network.utils.Auth
import com.example.ricardo.tickit2.data.network.utils.Config
import com.example.ricardo.tickit2.extensions.applySchedulers
import com.example.ricardo.tickit2.extensions.plusAssign
import com.example.ricardo.tickit2.extensions.subscribeBy
import com.example.ricardo.tickit2.greendao.gen.GDUserDao
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ricardo on 2017/12/17.
 */

class PhotoChosePresenter(val view: PhotoChoseView,val respository: UserRepository): PhotoChoseContract.Presenter{
    var userDao: GDUserDao? = null

    protected var subscriptins = CompositeDisposable()

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
                val headpicPath = "http://" + Config.TEST_DOMAIN + "/" + key

                view.postAvatarSuccess(headpicPath)

            }
            else{
                view.postAvatarError(null)
            }

        }, null)


    }
    override fun updateUserInfo(avatarPath: String) {

        val db = userDao!!.queryBuilder()

        val list = db.list()

        val gdUser = userDao!!.load(list[0].id)

        var nUser = User(gdUser)

        nUser.avatar = avatarPath

        subscriptins += respository.updateUserInfo(nUser)
                .applySchedulers()
                .subscribeBy (
                        onSuccess = view::onSuccess,
                        onError = view::onError
                )


    }

    override fun onViewDestroyed() {

    }

}