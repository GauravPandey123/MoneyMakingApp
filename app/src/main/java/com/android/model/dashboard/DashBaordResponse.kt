package com.android.model.dashboard

import com.google.gson.annotations.SerializedName
import com.android.model.dashboard.ServicesItem

data class DashBaordResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("services")
	val services: ArrayList<ServicesItem>
)