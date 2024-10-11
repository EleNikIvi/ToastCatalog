package com.sumup.challenge.toastcatalog.data.remote

import com.sumup.challenge.toastcatalog.data.remote.base.GenericResponse
import retrofit2.http.GET

interface ItemApi {
    @GET("items")
    suspend fun getItems(): GenericResponse<List<ItemDto>>
}
