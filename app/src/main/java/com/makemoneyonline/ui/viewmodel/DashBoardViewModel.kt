package com.makemoneyonline.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.makemoneyonline.networkservice.ApiResponse
import com.makemoneyonline.ui.repository.DashBoadRepository

class DashBoardViewModel(application: Application) : AndroidViewModel(application) {

    private val dashBoardRepository: DashBoadRepository by lazy {
        DashBoadRepository()
    }


    fun dashBoarddata(): MutableLiveData<ApiResponse> {
        return dashBoardRepository.DashBoardRespository()
    }

}