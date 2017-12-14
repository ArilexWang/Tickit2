package com.example.ricardo.tickit2

import android.app.Application
import com.example.ricardo.tickit2.greendao.gen.DaoMaster
import com.example.ricardo.tickit2.greendao.gen.DaoSession
import kotlin.properties.Delegates

/**
 * Created by Ricardo on 2017/11/10.
 */

class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()
    }

    private var daoSession: DaoSession? = null
    private var daoMaster: DaoMaster? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        setDatebase()
    }

    fun setDatebase(){
        val dbHelper = DaoMaster.DevOpenHelper(this,"user")
        val db = dbHelper.writableDb
        daoMaster = DaoMaster(db)
        daoSession = DaoMaster(db).newSession()

    }
    open fun getDaoSession(): DaoSession{
        return daoSession!!
    }


}