package com.android.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.model.dashboard.ServicesItem
import com.android.networkservice.ApiResponse
import com.android.ui.repository.DashBoadRepository

class DashBoardViewModel(application: Application) : AndroidViewModel(application) {

    private val feedsLiveData: LiveData<PagedList<ServicesItem>>

    private val dashBoardRepository: DashBoadRepository by lazy {
        DashBoadRepository()
    }

    private val boundaryCallback: PagedList.BoundaryCallback<ServicesItem>


    fun dashBoarddata(): MutableLiveData<ApiResponse> {
        return dashBoardRepository.DashBoardRespository()
    }

    val showFeedLoading = MutableLiveData<Boolean>()
    init {
        val factory: DataSource.Factory<Int,ServicesItem>
                =dashBoardRepository.makeMoneyDao.getAllFeedsPaged()
        boundaryCallback=object : PagedList.BoundaryCallback<ServicesItem>()
        {
            override fun onZeroItemsLoaded() {
                super.onZeroItemsLoaded()
                Log.e("mytag","onZeroItemsLoaded")
                showFeedLoading.postValue(true)
                dashBoarddata().observeForever{
                    showFeedLoading.postValue(false)
                }
            }

        }

        val pagedListBuilder: LivePagedListBuilder<Int, ServicesItem> = LivePagedListBuilder(factory,10)
            .setBoundaryCallback(boundaryCallback)
        feedsLiveData = pagedListBuilder.build()
    }

    fun deleteAllFeeds()
    {
        dashBoardRepository.deleteAllFeeds()
    }



}