package com.makemoneyonline.ui.repository

import androidx.lifecycle.MutableLiveData
import com.makemoneyonline.datasource.getDashBoardData
import com.makemoneyonline.datasource.onError
import com.makemoneyonline.datasource.onSuccess
import com.makemoneyonline.networkservice.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DashBoadRepository {
    fun DashBoardRespository(): MutableLiveData<ApiResponse> {

        val result = MutableLiveData<ApiResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val request = getDashBoardData()
            request.onSuccess {
                result.postValue(ApiResponse(it, null))
            }

            request.onError {
                result.postValue(ApiResponse(null, it))

            }
        }
        return result
    }
}