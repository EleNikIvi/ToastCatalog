package com.sumup.challenge.toastcatalog.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDto(
    val id: Int,
    val name: String,
    val price: String,
    val currency: String,
    @Json(name = "last_sold")
    val lastSold: String,
)
