package com.android

import android.app.Application
import com.android.data.database.MakeMoneyOnlineDb


val makemoneyOnlineDb:MakeMoneyOnlineDb by lazy {
    BaseApplication.makeMoneyDataBase!!
}

class BaseApplication : Application() {

    companion object{
        var makeMoneyDataBase:MakeMoneyOnlineDb?=null

    }

    override fun onCreate() {
        super.onCreate()
        init()

    }

    private fun init() {
        makeMoneyDataBase = MakeMoneyOnlineDb.getInstance(applicationContext)
    }
}