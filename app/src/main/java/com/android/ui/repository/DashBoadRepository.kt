package com.android.ui.repository

import androidx.lifecycle.MutableLiveData
import com.android.data.dao.MakeMoneyOnlineDao
import com.android.datasource.getDashBoardData
import com.android.datasource.onError
import com.android.datasource.onSuccess
import com.android.makemoneyOnlineDb
import com.android.model.dashboard.ServicesItem
import com.android.networkservice.ApiResponse
import kotlinx.coroutines.*
import java.util.concurrent.Executors


class DashBoadRepository {

    val makeMoneyDao : MakeMoneyOnlineDao by lazy {
        makemoneyOnlineDb.makeMoneyOnlineDao()
    }



    fun deleteAllFeeds()
    {
        CoroutineScope(Dispatchers.IO).launch {
            makeMoneyDao.deleteServiceTable()
        }
    }


    fun addFeeds(feedsList:List<ServicesItem>)
    {
        feedsList.forEach {
            val executor = Executors.newSingleThreadExecutor()
            executor.execute {
                makeMoneyDao.addData(it)
            }
        }
    }

    suspend fun getAllFeedsFromDb():List<ServicesItem> = GlobalScope.async {
        return@async makeMoneyDao.getAllData()
    }.await()


    fun syncFeeds(feedsList: List<ServicesItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            val feedsDb=getAllFeedsFromDb()
            val diffList=feedsDb.subtract(feedsList)
            diffList.forEach {
                makeMoneyDao.deleteFeed(it)
            }
        }
    }

    fun DashBoardRespository(): MutableLiveData<ApiResponse> {
        val result = MutableLiveData<ApiResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val request = getDashBoardData()
            request.onSuccess {
                addFeeds(it.services)
                result.postValue(ApiResponse(it, null))
            }

            request.onError {
                result.postValue(ApiResponse(null, it))

            }
        }
        return result
    }
}