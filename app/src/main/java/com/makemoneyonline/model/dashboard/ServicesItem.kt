package com.makemoneyonline.model.dashboard

import com.google.gson.annotations.SerializedName

data class ServicesItem(

	@field:SerializedName("order_no")
	val orderNo: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)