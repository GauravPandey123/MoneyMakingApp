package com.makemoneyonline.model.dashboard

import com.google.gson.annotations.SerializedName

data class DashBaordResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("services")
	val services: ArrayList<ServicesItem>
)