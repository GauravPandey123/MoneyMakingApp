package com.android.model.dashboard

import com.google.gson.annotations.SerializedName

data class ServicesItem(

	@field:SerializedName("way_no")
	val wayNo: String? = null,

	@field:SerializedName("service_image")
	val image: String? = null,

	@field:SerializedName("icon_image")
	val iconimage: String? = null,

	@field:SerializedName("created")
	val created: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("service_id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)