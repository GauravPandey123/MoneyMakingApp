package com.android.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.networkservice.ApiResponse
import com.android.ui.repository.DashBoadRepository

class DashBoardViewModel(application: Application) : AndroidViewModel(application) {

    private val dashBoardRepository: DashBoadRepository by lazy {
        DashBoadRepository()
    }


    fun dashBoarddata(): MutableLiveData<ApiResponse> {
        return dashBoardRepository.DashBoardRespository()
    }

}