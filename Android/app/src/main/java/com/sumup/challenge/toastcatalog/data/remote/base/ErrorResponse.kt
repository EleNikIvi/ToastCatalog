package com.sumup.challenge.toastcatalog.data.remote.base

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val errorCode: String,
    val errorMessage: String
) {
    companion object {
        val UNKNOWN = ErrorResponse("-1", "Unknown")
    }
}