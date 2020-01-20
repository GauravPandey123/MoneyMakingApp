package com.android.data.dao

import androidx.paging.DataSource
import androidx.room.*
import com.android.model.dashboard.ServicesItem

@Dao
interface MakeMoneyOnlineDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(servicesItem: ServicesItem)

    @Query("SELECT * FROM service_table ORDER BY created DESC")
    fun getAllData() : List<ServicesItem>

    @Delete
    fun deleteFeed(servicesItem: ServicesItem)

    @Query("DELETE FROM service_table")
    fun deleteServiceTable()


    @Query("SELECT * FROM service_table ORDER BY created DESC")
    fun getAllFeedsPaged(): DataSource.Factory<Int,ServicesItem>
}