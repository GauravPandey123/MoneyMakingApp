package com.android.model.dashboard

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.lang.NumberFormatException

@Entity(tableName = "service_table")
data class ServicesItem(

	@field:SerializedName("way_no")
	val wayNo: String?,

	@field:SerializedName("service_image")
	val image: String? ,

	@field:SerializedName("icon_image")
	val iconimage: String? ,

	@field:SerializedName("created")
	val created: String? ,

	@field:SerializedName("description")
	val description: String? ,

	@PrimaryKey
	@NonNull
	@field:SerializedName("service_id")
	val id: String,

	@field:SerializedName("title")
	val title: String? ,

	@field:SerializedName("updated")
	val updated: String? ,

	@field:SerializedName("status")
	val status: String?
) {
	override fun equals(other: Any?): Boolean {
		return this.id == (other as ServicesItem).id
	}

	override fun hashCode(): Int {
		return try {
			id.hashCode()
		} catch (e: NullPointerException) {
			0
		} catch (e: NumberFormatException) {
			0
		}

	}
}