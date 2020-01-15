package com.android.networkservice

import com.android.model.dashboard.DashBaordResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("get-services.php")
    fun getDashBoardData(): Call<DashBaordResponse>

}