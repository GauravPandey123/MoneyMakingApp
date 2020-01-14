package com.makemoneyonline.networkservice

import com.makemoneyonline.model.dashboard.DashBaordResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

    @GET("get-services.php")
    fun getDashBoardData(): Call<DashBaordResponse>

}