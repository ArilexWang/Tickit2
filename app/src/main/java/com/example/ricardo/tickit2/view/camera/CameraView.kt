package com.example.ricardo.tickit2.view.camera

import com.example.ricardo.tickit2.data.model.User

/**
 * Created by Ricardo on 2017/12/2.
 */

interface CameraView {
    fun uploadSuccess(path: String)
    fun uploadError(error: String)
    fun onSuccess(items: List<User>)
    fun onError(error: Throwable)

}