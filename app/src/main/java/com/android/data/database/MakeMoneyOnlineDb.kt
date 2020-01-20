package com.android.data.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.data.dao.MakeMoneyOnlineDao
import com.android.model.dashboard.ServicesItem

@Database(entities = [ServicesItem::class],version = 1,exportSchema = false)
abstract class MakeMoneyOnlineDb : RoomDatabase() {

    abstract fun makeMoneyOnlineDao():MakeMoneyOnlineDao

    companion object{
        var instance:MakeMoneyOnlineDb? = null
        fun getInstance(context: Context):MakeMoneyOnlineDb{
            if (instance==null){
                synchronized(MakeMoneyOnlineDb::class){
                    instance = Room.databaseBuilder(context.applicationContext,MakeMoneyOnlineDb::class.java,"makemoneyonlinedb")
                        .fallbackToDestructiveMigration().build()
                }
            }
            return instance!!
        }
    }
}