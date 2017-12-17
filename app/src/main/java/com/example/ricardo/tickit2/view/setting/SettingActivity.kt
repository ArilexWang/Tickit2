package com.example.ricardo.tickit2.view.setting

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresPermission
import android.support.v4.app.ActivityCompat
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.Toast
import com.example.ricardo.tickit2.R
import com.example.ricardo.tickit2.base.BasePresenter
import com.example.ricardo.tickit2.data.network.repository.UserRepository
import com.example.ricardo.tickit2.view.common.BaseActivity
import com.github.florent37.camerafragment.CameraFragment
import com.github.florent37.camerafragment.CameraFragmentApi
import com.github.florent37.camerafragment.configuration.Configuration
import com.github.florent37.camerafragment.listeners.CameraFragmentControlsAdapter
import com.github.florent37.camerafragment.listeners.CameraFragmentResultAdapter
import com.github.florent37.camerafragment.listeners.CameraFragmentStateAdapter
import com.github.florent37.camerafragment.listeners.CameraFragmentVideoRecordTextAdapter
import com.github.florent37.camerafragment.widgets.CameraSettingsView
import kotlinx.android.synthetic.main.activity_setting.*
import java.io.File
import java.util.jar.Manifest


class SettingActivity:BaseActivity(), SettingView{
    override val presenter by lazy { SettingPresenter(this) }

    val settingView:CameraSettingsView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_setting)

        onAddCameraClicked()
        addCameraButton.setOnClickListener{ onAddCameraClicked()  }
        record_button.setOnClickListener{ onRecordButtonClicked() }
        front_back_camera_switcher.setOnClickListener{ onSwitchCameraClicked() }


    }

    fun onAddCameraClicked(){
        if(Build.VERSION.SDK_INT > 15){
            val permissions: Array<String>  = arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)

            var permissionsToRequest: MutableList<String> = mutableListOf()

            for (permission:String in permissions){
                if (ActivityCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                    permissionsToRequest!!.add(permission)
                }
            }
            if (!permissionsToRequest.isEmpty()){
                ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), REQUEST_CAMERA_PERMISSIONS)
            } else addCamera()
        } else {
            addCamera()
        }
    }

    fun onRecordButtonClicked() {
        val cameraFragment = getCameraFragment()
        if (cameraFragment != null) {
            cameraFragment!!.takePhotoOrCaptureVideo(object : CameraFragmentResultAdapter() {
                override fun onVideoRecorded(filePath: String?) {
                    Toast.makeText(baseContext, "onVideoRecorded " + filePath!!, Toast.LENGTH_SHORT).show()
                }

                override fun onPhotoTaken(bytes: ByteArray?, filePath: String?) {
                    Toast.makeText(baseContext, "onPhotoTaken " + filePath!!, Toast.LENGTH_SHORT).show()
                    println(filePath)
                    presenter.postAvatar(filePath)
                }
            },
                    "/storage/self/primary",
                    "photo0")
        }

    }



    fun onSwitchCameraClicked() {
        val cameraFragment = getCameraFragment()
        cameraFragment?.switchCameraTypeFrontBack()
    }


    @RequiresPermission(android.Manifest.permission.CAMERA)
    fun addCamera(){


        addCameraButton.visibility = View.GONE

        cameraLayout.visibility = View.VISIBLE

        val cameraFragment: CameraFragment = CameraFragment.newInstance(Configuration.Builder().setCamera(Configuration.CAMERA_FACE_REAR).build())


        supportFragmentManager.beginTransaction()
                .replace(R.id.content,cameraFragment, FRAGMENT_TAG)
                .commitAllowingStateLoss()


        cameraFragment.setStateListener(object : CameraFragmentStateAdapter() {

            override fun onCurrentCameraBack() {
                front_back_camera_switcher.displayBackCamera()
            }

            override fun onCurrentCameraFront() {
                front_back_camera_switcher.displayFrontCamera()
            }

            override fun onFlashAuto() {
                flash_switch_view.displayFlashAuto()
            }

            override fun onFlashOn() {
                flash_switch_view.displayFlashOn()
            }

            override fun onFlashOff() {
                flash_switch_view.displayFlashOff()
            }

            override fun onCameraSetupForPhoto() {
                photo_video_camera_switcher.displayActionWillSwitchVideo()

                record_button.displayPhotoState()
                flash_switch_view.setVisibility(View.VISIBLE)
            }

            override fun onCameraSetupForVideo() {
                photo_video_camera_switcher.displayActionWillSwitchPhoto()

                record_button.displayVideoRecordStateReady()
                flash_switch_view.setVisibility(View.GONE)
            }

            override fun shouldRotateControls(degrees: Int) {
                ViewCompat.setRotation(front_back_camera_switcher, degrees.toFloat())
                ViewCompat.setRotation(photo_video_camera_switcher, degrees.toFloat())
                ViewCompat.setRotation(flash_switch_view, degrees.toFloat())
                ViewCompat.setRotation(record_duration_text, degrees.toFloat())
                ViewCompat.setRotation(record_size_mb_text, degrees.toFloat())
            }

            override fun onRecordStateVideoReadyForRecord() {
                record_button.displayVideoRecordStateReady()
            }

            override fun onRecordStateVideoInProgress() {
                record_button.displayVideoRecordStateInProgress()
            }

            override fun onRecordStatePhoto() {
                record_button.displayPhotoState()
            }

            override fun onStopVideoRecord() {
                record_size_mb_text.setVisibility(View.GONE)
                //cameraSwitchView.setVisibility(View.VISIBLE);
                settings_view.setVisibility(View.VISIBLE)
            }

            override fun onStartVideoRecord(outputFile: File?) {}
        })

        cameraFragment.setControlsListener(object : CameraFragmentControlsAdapter() {
            override fun lockControls() {
                front_back_camera_switcher.setEnabled(false)
                record_button.setEnabled(false)
                settings_view.setEnabled(false)
                flash_switch_view.setEnabled(false)
            }

            override fun unLockControls() {
                front_back_camera_switcher.setEnabled(true)
                record_button.setEnabled(true)
                settings_view.setEnabled(true)
                flash_switch_view.setEnabled(true)
            }

            override fun allowCameraSwitching(allow: Boolean) {
                front_back_camera_switcher.setVisibility(if (allow) View.VISIBLE else View.GONE)
            }

            override fun allowRecord(allow: Boolean) {
                record_button.setEnabled(allow)
            }

            override fun setMediaActionSwitchVisible(visible: Boolean) {
                photo_video_camera_switcher.setVisibility(if (visible) View.VISIBLE else View.INVISIBLE)
            }
        })

        cameraFragment.setTextListener(object : CameraFragmentVideoRecordTextAdapter() {
            override fun setRecordSizeText(size: Long, text: String?) {
                record_size_mb_text.setText(text)
            }

            override fun setRecordSizeTextVisible(visible: Boolean) {
                record_size_mb_text.setVisibility(if (visible) View.VISIBLE else View.GONE)
            }

            override fun setRecordDurationText(text: String?) {
                record_duration_text.setText(text)
            }

            override fun setRecordDurationTextVisible(visible: Boolean) {
                record_duration_text.setVisibility(if (visible) View.VISIBLE else View.GONE)
            }
        })

    }


    fun getCameraFragment(): CameraFragmentApi{
        return supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) as CameraFragmentApi
    }

    companion object {
        val REQUEST_CAMERA_PERMISSIONS: Int = 931
        val FRAGMENT_TAG = "camera"
    }
}
