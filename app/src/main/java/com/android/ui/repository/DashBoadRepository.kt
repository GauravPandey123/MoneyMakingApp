package com.android.ui.repository

import androidx.lifecycle.MutableLiveData
import com.android.datasource.getDashBoardData
import com.android.datasource.onError
import com.android.datasource.onSuccess
import com.android.networkservice.ApiResponse
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