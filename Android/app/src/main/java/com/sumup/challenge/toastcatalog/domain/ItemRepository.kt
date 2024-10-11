package com.sumup.challenge.toastcatalog.domain

import com.sumup.challenge.toastcatalog.domain.model.ItemModel
import com.sumup.challenge.toastcatalog.domain.model.Result

interface ItemRepository {
    suspend fun getItems(): Result<List<ItemModel>>
}